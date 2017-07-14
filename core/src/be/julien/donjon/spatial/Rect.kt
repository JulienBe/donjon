package be.julien.donjon.spatial

import be.julien.donjon.util.Rnd
import com.badlogic.gdx.math.Rectangle

class Rect(x: Float, y: Float, width: Float, height: Float) : Rectangle(x, y, width, height) {
    companion object {
        fun rndPos(width: Float, height: Float): Rect {
            return Rect(Rnd.col(), Rnd.row(), width, height)
        }
    }
}