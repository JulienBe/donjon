package be.julien.donjon.life

import be.julien.donjon.spatial.Rect
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.util.Rnd

class MostBasic(rect: Rect, dir: Vec2,  val right: Float, val left: Float, val forward: Float) : Life(rect, dir) {

    override fun act(delta: Float) {
        super.act(delta)
        val leftRoll = Rnd.float(left)
        val rightRoll = Rnd.float(right)
        val forwardRoll = Rnd.float(forward)
        if (leftRoll > rightRoll && leftRoll > rightRoll)
            steer(90f)
        else if (rightRoll > forwardRoll)
            steer(-90f)
    }

}