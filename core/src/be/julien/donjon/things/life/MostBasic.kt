package be.julien.donjon.things.life

import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.sensors.RoundSensor
import be.julien.donjon.things.sensors.Sensor

class MostBasic(pos: Vec2, dir: Vec2) : Life(pos, dir) {

    internal val steeringSpeed = 2f
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
        if (s.colliders.count() > 0) {
            val e = s.getEnergy()
            if (e != null)
                goTowards(e, delta)
            else
                goAwayFrom(s.colliders[0], delta)
        }
        s.checked()
    }

    companion object {
        val dim = Dimension.get(2f, 2f)
    }
}