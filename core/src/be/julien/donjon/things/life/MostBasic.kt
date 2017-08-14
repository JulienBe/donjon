package be.julien.donjon.things.life

import be.julien.donjon.physics.Physics
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.Energy
import be.julien.donjon.things.WallAO
import be.julien.donjon.things.sensors.RoundSensor
import be.julien.donjon.things.sensors.Sensor

class MostBasic(pos: Vec2, dir: Vec2) : Life(pos, dir) {

    internal val wallBase = 1f
    internal val wallMod = 1f
    internal val energyMod = 1f
    internal val energyValueMod = 1f
    internal val energyDistMod = 1f
    private val sensor = RoundSensor.get(this, 8f)

    init {
        sensors.add(sensor)
    }

    override fun dimension(): Dimension = dim

    override fun act(delta: Float): Boolean {
        colliders(sensor, delta)
        return super.act(delta)
    }

    private fun colliders(s: Sensor, delta: Float) {
        val target = Vec2.tmp
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