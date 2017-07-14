package be.julien.donjon.life

import be.julien.donjon.spatial.Vec2
import be.julien.donjon.util.Rnd

class MostBasic(pos: Vec2, dir: Vec2, deadCallback: (life: Life) -> Unit, val right: Float, val left: Float, val forward: Float) : Life(pos, dir, deadCallback) {

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