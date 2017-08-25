package be.julien.donjon.util.spatial

import be.julien.donjon.GdxArr
import be.julien.donjon.physics.Physics
import be.julien.donjon.things.Thing
import be.julien.donjon.util.Rnd
import com.badlogic.gdx.math.Vector2

class Vec2 private constructor(x: Float, y: Float) {

    private val v = Vector2(x, y)
    private val previous = Vector2(x, y)

    fun move(dir: Vec2, delta: Float) {
        v.x += dir.x() * delta
        v.y += dir.y() * delta
    }

    fun move(x: Float, y: Float) {
        v.x += x
        v.y += y
    }

    fun x(): Float {
        return v.x
    }
    fun y(): Float {
        return v.y
    }

    fun steerLeft(): Vec2 {
        v.rotate90(1)
        return this
    }

    fun rollback(viscosity: Float) {
        v.lerp(previous, viscosity)
    }

    fun validate() {
        previous.set(v)
    }

    fun steerRight(): Vec2 {
        v.rotate90(-1)
        return this
    }

    fun steer(angleDegree: Float, delta: Float): Vec2 {
        v.rotate(angleDegree * delta)
        return this
    }

    fun set(x: Float, y: Float): Vec2 {
        v.set(x, y)
        return this
    }

    fun set(other: Vec2): Vec2 {
        set(other.x(), other.y())
        return this
    }

    fun angle(): Float {
        return v.angle()
    }

    fun dst(other: Vec2): Float = v.dst(other.v)
    fun dst(x: Float, y: Float): Float = v.dst(x, y)

    fun nor(): Vec2 {
        v.nor()
        return this
    }

    internal fun rotate(angle: Float): Vec2 {
        v.rotate(angle)
        return this
    }
    private fun setToRandomDirection(): Vec2 {
        v.set(0f, 1f).rotate(Rnd.float(360f))
        return this
    }

    fun add(other: Vec2): Vec2 {
        v.add(other.x(), other.y())
        return this
    }

    fun dot(other: Vec2): Float {
        return v.dot(other.v)
    }

    fun scl(f: Float): Vec2 {
        v.scl(f)
        return this
    }

    fun setAngle(f: Float): Vec2 {
        v.setAngle(f)
        return this
    }

    fun isLeftCloser(other: Vec2): Boolean {
        return isLeftCloser(other.angle())
    }

    fun isLeftCloser(angleOther: Float): Boolean {
        val myAngle = v.angle()
        val upper = (myAngle + 180f) % 360f
        if (myAngle < 180f)
            return angleOther > myAngle && angleOther < upper
        else
            return angleOther > myAngle || angleOther < upper
    }

    companion object {
        val tmp = Vec2(0f, 0f)
        val zero = Vec2(0f, 0f)

        fun get(x: Float, y: Float): Vec2 {
            return Vec2(x, y)
        }

        fun rnd(): Vec2 {
            return get(0f, 1f).setToRandomDirection()
        }

        fun getRandWorld(): Vec2 {
            return get(Rnd.width(), Rnd.height())
        }

        fun getRnd(): Vec2 {
            return get(0f, 1f).steer(Rnd.float(360f), 1f)
        }

        fun getRandWorld(excluded: GdxArr<Thing>): Vec2 {
            (1..50).forEach {
                val v = getRandWorld()
                excluded.forEach {
                    if (!Physics.contains(it, v))
                        return v
                }
            }
            return getRandWorld()
        }

        fun get(angle: Float): Vec2 {
            return Vec2(1f, 0f).rotate(angle)
        }
    }

    fun setX(x: Float) {
        v.x = x
    }
    fun setY(y: Float) {
        v.y = y
    }

    fun len(): Float = v.len()

    fun rotate90(i: Int): Vec2 {
        v.rotate90(i)
        return this
    }

    override fun toString(): String {
        return "Vec2(v=$v)"
    }

}