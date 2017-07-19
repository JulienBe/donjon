package be.julien.donjon.things.life

import be.julien.donjon.spatial.Rect
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.Sensor

class MostBasic(rect: Rect, dir: Vec2) : Life(rect, dir) {

    internal val right = Sensor.get(this, 3f, 45f, 1f)
    internal val left = Sensor.get(this, 3f, -45f, 1f)
    internal val straight = Sensor.get(this, 4f, 0f, 1.5f)
    internal val steeringSpeed = 2f

    init {
        sensors.add(left)
        sensors.add(right)
        sensors.add(straight)
    }

    override fun act(delta: Float): Boolean {
        sensors.forEach { colliders(it, delta) }
        return super.act(delta)
    }

    private fun colliders(s: Sensor, delta: Float) {
        if (s.colliders.count() > 0) {
            steer(s.offsetAngle + 180, delta * steeringSpeed)
            s.checked()
        }
    }

}