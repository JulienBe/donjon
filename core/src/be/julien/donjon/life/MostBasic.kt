package be.julien.donjon.life

import be.julien.donjon.physics.b2d.BoxHelper
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.util.Rnd

class MostBasic(pos: Vec2, dir: Vec2, deadCallback: (life: Life) -> Unit, val right: Float, val left: Float, val forward: Float) : Life(pos, dir, deadCallback) {

    internal val sensors = BoxHelper.createSensor(body)

    override fun act(delta: Float) {
        super.act(delta)
        val leftRoll = Rnd.float(left)
        val rightRoll = Rnd.float(right)
        val forwardRoll = Rnd.float(forward)
        if (leftRoll > rightRoll && leftRoll > rightRoll)
            body.steer(90f)
        else if (rightRoll > forwardRoll)
            body.steer(-90f)
    }

}