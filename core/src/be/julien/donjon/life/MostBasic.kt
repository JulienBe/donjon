package be.julien.donjon.life

import be.julien.donjon.spatial.Direction
import be.julien.donjon.spatial.Position
import be.julien.donjon.util.Rnd

class MostBasic(pos: Position, val right: Float = Rnd.float(), val left: Float = Rnd.float(), val forward: Float = Rnd.float(), var direction: Direction = Rnd.direction()) : Life(pos) {

    override fun act() {
        val leftRoll = Rnd.float(left)
        val rightRoll = Rnd.float(right)
        val forwardRoll = Rnd.float(forward)
        if (leftRoll > rightRoll && leftRoll > rightRoll)
            direction.steerLeft()
        else if (rightRoll > forwardRoll)
            direction.steerRight()
        else
            pos.move(direction)
    }

}