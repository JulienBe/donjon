package be.julien.donjon.things.life

import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.sensors.SquareSensor

class MostBasic(pos: Vec2, dir: Vec2) : Life(pos, dir) {

    internal val right = SquareSensor.get(this, 3f, 45f, 1f)
    internal val left = SquareSensor.get(this, 3f, -45f, 1f)
    internal val straight = SquareSensor.get(this, 4f, 0.1f, 1.5f)
    internal val steeringSpeed = 2f

    init {
        sensors.add(left)
        sensors.add(right)
        sensors.add(straight)
    }

    override fun dimension(): Dimension = dim

    override fun act(delta: Float): Boolean {
        sensors.forEach { colliders(it, delta) }
        return super.act(delta)
    }

    private fun colliders(s: SquareSensor, delta: Float) {
        if (s.colliders.count() > 0) {
            if (s.containersEnergy())
                steer(s.offsetAngle, delta * steeringSpeed)
            else
                steer(Math.signum(s.offsetAngle) * -180f, delta * steeringSpeed)
        }
        s.checked()
    }

    companion object {
        val dim = Dimension.get(2f, 2f)
    }
}