package be.julien.donjon.life

import be.julien.donjon.spatial.Vec2
import be.julien.donjon.util.Rnd

class MostBasic(pos: Vec2, deadCallback: (life: Life) -> Unit, val right: Float, val left: Float, val forward: Float, var dir: Vec2) : Life(pos, deadCallback) {

    override fun act(delta: Float) {
        super.act(delta)
        val leftRoll = Rnd.float(left)
        val rightRoll = Rnd.float(right)
        val forwardRoll = Rnd.float(forward)
        if (leftRoll > rightRoll && leftRoll > rightRoll)
            dir.steer(90f, delta)
        else if (rightRoll > forwardRoll)
            dir.steer(-90f, delta)
        pos.move(dir, delta)
    }

}