package be.julien.donjon.things.life

import be.julien.donjon.graphics.AssetMan
import be.julien.donjon.lifesim.DNA
import be.julien.donjon.physics.Physics
import be.julien.donjon.things.Energy
import be.julien.donjon.things.sensors.Sensor
import be.julien.donjon.world.Collect
import be.julien.seed.Dimension
import be.julien.seed.Thing
import be.julien.seed.Vec2
import be.julien.seed.WallAO
import be.julien.seed.sensors.RoundSensor

class MostBasic(pos: Vec2, dir: Vec2, dna: DNA = DNA()) : Life(pos, dir, dna) {

    /*
    0.9051367, 0.9773993, 0.79385370, 1.10078560, 1.0269687, 0.9688986, 0.70713985,
    0.8363497, 0.9080985, 0.82114320, 1.05114350, 1.0641985, 1.0684454, 0.6140407,
    0.8813086, 0.9116909, 0.77517015, 0.96981364, 1.0421330, 1.0323613, 0.5678359,
     */
    internal val wallBase = dna.genes[1] // 0.87 0.87 0.85
    internal val wallMod = dna.genes[2] // 1.09 1.1 1.15
    internal val energyMod = dna.genes[3] // 1.07 1.10 1.07
    internal val energyValueMod = dna.genes[4] // 0.90 0.83 0.88
    internal val energyDistMod = dna.genes[5]
    internal val energyReproduction = dna.genes[6]
    internal val otherLifeBase = dna.genes[7]
    internal val canReproduceMod = dna.genes[8]
    internal val otherLifeMod = dna.genes[9]
    private val sensor = RoundSensor.get(this, 8f)

    override fun energyStealVal(): Int {
        return 3
    }

    init {
        sensors.add(sensor)
    }

    override fun dimension(): Dimension = dim
    override fun img(): Any {
        return AssetMan.square
    }

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
        val otherDir = Vec2.get(dir.x(), dir.y())
        otherDir.rotate(-45f)
        Collect.add(mostBasic(Vec2.get(pos.x(), pos.y()), otherDir, mix))
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
                is MostBasic -> if (canReproduce()) targetValue = compareOtherLife(targetValue, target, it)
            }
        }
        dir.add(target)
        norSpeed()
        s.checked()
    }

    private fun compareOtherLife(currentTargetValue: Float, target: Vec2, other: MostBasic): Float {
        var newValue = otherLifeBase
        if (currentTargetValue > newValue) {
            return currentTargetValue
        } else {
            target.set(other.centerX(), other.centerY()).nor().scl(otherLifeMod)
            return newValue
        }
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
            target.set(1f, 0f).rotate(wall.normal(this).angle).scl(wallMod)
            return newValue
        }
    }

    companion object {
        val dim = Dimension.get(0.5f, 0.5f)
    }
}