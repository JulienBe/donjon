package be.julien.donjon.spatial

import be.julien.donjon.util.Rnd
import com.badlogic.gdx.math.Rectangle

class Rect internal constructor(x: Float, y: Float, width: Float, height: Float) : Rectangle(x, y, width, height) {
    fun centerX(): Float = x + width / 2f
    fun centerY(): Float = y + height / 2f

    fun set(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    companion object {
        fun rndPos(width: Float, height: Float): Rect {
            return Rect(Rnd.width(), Rnd.height(), width, height)
        }

        fun get(x: Float, y: Float, width: Float, height: Float): Rect {
            return Rect(x, y, width, height)
        }
        fun get(width: Float): Rect {
            return Rect(-width, -width, width, width)
        }
    }

}