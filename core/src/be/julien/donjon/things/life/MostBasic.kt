package be.julien.donjon.things.life

import be.julien.donjon.spatial.Rect
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.Sensor
import be.julien.donjon.util.Rnd

class MostBasic(rect: Rect, dir: Vec2, val right: Float, val left: Float, val forward: Float) : Life(rect, dir) {

    init {
        sensors.add(Sensor.get(this, 3f, 45f, 0.8f))
        sensors.add(Sensor.get(this, 3f, -45f, 0.8f))
    }

    override fun act(delta: Float): Boolean {
        val leftRoll = Rnd.float(left)
        val rightRoll = Rnd.float(right)
        val forwardRoll = Rnd.float(forward)
        if (leftRoll > rightRoll && leftRoll > rightRoll)
            steer(90f * delta)
        else if (rightRoll > forwardRoll)
            steer(-90f * delta)
        return super.act(delta)
    }

}