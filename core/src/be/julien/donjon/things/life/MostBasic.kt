package be.julien.donjon.things.life

import be.julien.donjon.lifesim.DNA
import be.julien.donjon.physics.Physics
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.Energy
import be.julien.donjon.things.Thing
import be.julien.donjon.things.WallAO
import be.julien.donjon.things.sensors.RoundSensor
import be.julien.donjon.things.sensors.Sensor
import be.julien.donjon.world.Collect

class MostBasic(pos: Vec2, dir: Vec2, dna: DNA = DNA()) : Life(pos, dir, dna) {

    internal val wallBase = dna.genes[1]
    internal val wallMod = dna.genes[2]
    internal val energyMod = dna.genes[3]
    internal val energyValueMod = dna.genes[4]
    internal val energyDistMod = dna.genes[5]
    internal val energyReproduction = dna.genes[6]
    private val sensor = RoundSensor.get(this, 8f)

    init {
        sensors.add(sensor)
    }

    override fun dimension(): Dimension = dim

    override fun act(delta: Float): Boolean {
        colliders(sensor, delta)
        return super.act(delta)
    }

    override fun collidesWith(thing: Thing) {
        super.collidesWith(thing)
        if (thing is MostBasic && canReproduce()) {
            reproduce(thing)
        }
    }

    override fun reproduce(life: Life) {
        val mix = dna.mix(life.dna)
        val otherDir = Vec2.get(dir.x, dir.y)
        otherDir.rotate(-45f)
        Collect.add(mostBasic(Vec2.get(pos.x, pos.y), otherDir, mix))
        dir.rotate(45f)
        energy.step(51)
        super.reproduce(life)
    }

    private fun colliders(s: Sensor, delta: Float) {
        val target = Vec2.tmp
        target.set(0f, 0f)
        var targetValue = 0f
        s.colliders.forEach { it ->
            when (it) {
                is Energy -> targetValue = compareEnergy(targetValue, target, it)
                is WallAO -> targetValue = compareWall(targetValue, target, it)
            }
        }
        dir.add(target)
        norSpeed()
        s.checked()
    }

    private fun compareEnergy(currentTargetValue: Float, target: Vec2, energy: Energy): Float {
        var newValue = energy.getEnergy().toFloat() * energyValueMod
        newValue += Physics.distSq(this, energy) * energyDistMod

        if (currentTargetValue > newValue) {
            return currentTargetValue
        } else {
            target.set(dirCenter(energy)).nor().scl(energyMod)
            return newValue
        }
    }


    private fun compareWall(currentTargetValue: Float, target: Vec2, wall: WallAO): Float {
        var newValue = wallBase

        if (currentTargetValue > newValue) {
            return currentTargetValue
        } else {
            target.set(1f, 0f).rotate(wall.normal(this)).scl(wallMod)
            return newValue
        }
    }

    companion object {
        val dim = Dimension.get(2f, 2f)
    }
}