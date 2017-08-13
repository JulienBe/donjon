package be.julien.donjon.things.life

import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.Energy
import be.julien.donjon.things.WallAO
import be.julien.donjon.things.sensors.RoundSensor
import be.julien.donjon.things.sensors.Sensor

class MostBasic(pos: Vec2, dir: Vec2) : Life(pos, dir) {

    internal val wallRepulsion = 2f
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
        s.colliders.forEach { it ->
            when (it) {
                is Energy -> dir.add(dirCenter(it).nor())
                is WallAO -> dir.add(Vec2.get(it.normal(this)).scl(wallRepulsion))
            }
        }
        norSpeed()
        s.checked()
    }

    companion object {
        val dim = Dimension.get(2f, 2f)
    }
}