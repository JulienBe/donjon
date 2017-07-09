package be.julien.donjon.life

import be.julien.donjon.spatial.Vec2
import be.julien.donjon.spatial.Vec2Comp
import be.julien.donjon.util.Rnd

class MostBasic(pos: Vec2, val right: Float = Rnd.float(), val left: Float = Rnd.float(), val forward: Float = Rnd.float(), var dir: Vec2 = Vec2Comp.rnd()) : Life(pos) {

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