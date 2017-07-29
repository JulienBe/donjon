package be.julien.donjon.things.life

import be.julien.donjon.physics.Physics
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.sensors.RoundSensor
import be.julien.donjon.things.sensors.Sensor
import be.julien.donjon.things.sensors.SquareSensor

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
            val angle = Physics.angle(s, this)
            if (s.containersEnergy()) {
                steer(angle, delta * steeringSpeed)
            } else {
                steer(angle -180f, delta * steeringSpeed)
            }
        }
        s.checked()
    }

    companion object {
        val dim = Dimension.get(2f, 2f)
    }
}