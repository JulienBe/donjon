package be.julien.donjon.life

import be.julien.donjon.Util
import be.julien.donjon.spatial.Direction
import be.julien.donjon.spatial.Position

class MostBasic(pos: Position, val right: Float, val left: Float, val forward: Float, var direction: Direction) : Life(pos) {

    override fun act() {
        val leftRoll = Util.rnd.nextFloat() * left
        val rightRoll = Util.rnd.nextFloat() * right
        val forwardRoll = Util.rnd.nextFloat() * forward
        if (leftRoll > rightRoll && leftRoll > rightRoll)
            direction.steerLeft()
        else if (rightRoll > forwardRoll)
            direction.steerRight()
        else
            pos.move(direction)
    }



}