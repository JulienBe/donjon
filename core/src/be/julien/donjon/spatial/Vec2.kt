package be.julien.donjon.spatial

import be.julien.donjon.util.Rnd
import com.badlogic.gdx.math.Vector2

class Vec2 private constructor(x: Float, y: Float) : Vector2(x, y) {
    fun move(dir: Vec2, delta: Float) {
        x += dir.x * delta
        y += dir.y * delta
    }

    fun steerLeft() {
        rotate90(1)
    }

    fun steerRight() {
        rotate90(-1)
    }

    fun steer(angleDegree: Float, delta: Float): Vec2 {
        rotate(angleDegree * delta)
        return this
    }

    companion object {
        val tmp = Vec2(0f, 0f)

        fun get(x: Float, y: Float): Vec2 {
            return Vec2(x, y)
        }

        fun rnd(): Vec2 {
            return get(0f, 1f).setToRandomDirection() as Vec2
        }

        fun getRandWorld(): Vec2 {
            return get(Rnd.width(), Rnd.height())
        }

        fun getRnd(): Vec2 {
            return get(0f, 1f).steer(Rnd.float(360f), 1f)
        }
    }
}