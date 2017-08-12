package be.julien.donjon.physics

import be.julien.donjon.physics.shapes.Circle
import be.julien.donjon.physics.shapes.Square
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.Thing
import be.julien.donjon.util.Util

object Physics {
    fun angle(a: Thing, b: Thing): Float = Vec2.tmp.set(a.centerX() - b.centerX(), a.centerY() - b.centerY()).angle()

    fun checkCollision(a: Thing, b: Thing): Boolean {
        when(a.shape()) {
            Square -> return squareCheck(a, b)
            Circle -> return circleCheck(a, b)
        }
        return noCollision(a, b)
    }

    private fun noCollision(a: Thing, b: Any): Boolean {
        Util.err("Hu, collision mistake between $a and $b")
        return false
    }

    private fun circleCheck(circle: Thing, b: Thing): Boolean {
        when(b.shape()) {
            Square -> return squareCircle(b, circle)
        }
        return noCollision(circle, b)
    }

    private fun squareCheck(square: Thing, b: Thing): Boolean {
        when(b.shape()) {
            Square -> return squareSquare(square, b)
            Circle -> return squareCircle(square, b)
        }
        return noCollision(square, b)
    }

    private fun squareSquare(me: Thing, other: Thing): Boolean {
        return me.x() < other.x() + other.w() &&
                me.x() + me.w() > other.x() &&
                me.y() < other.y() + other.h() &&
                me.h() + me.y() > other.y()
    }

    private fun squareCircle(square: Thing, circle: Thing): Boolean {
        val distX = Math.abs(circle.centerX() - square.centerX())
        val distY = Math.abs(circle.centerY() - square.centerY())
        if (distX > (circle.hw() + square.hw()))
            return false
        if (distY > (circle.hh() + square.hh()))
            return false
        if (distX <= square.hw())
            return true
        if (distY <= circle.hh())
            return true
        val dx = distX - square.hw()
        val dy = distY - square.hh()
        return (dx * dx + dy * dy) <= (circle.hw() * circle.hw())
    }

    private fun vecInsideSquare(t: Thing, v: Vec2): Boolean {
        return t.x() < v.x &&
                t.x() + t.w() > v.x &&
                t.y() < v.y &&
                t.h() + t.y() > v.y
    }

    private fun vecInsideCircle(t: Thing, vec2: Vec2): Boolean {
        return vec2.dst(t.centerX(), t.centerY()) < t.w()
    }

    fun goAwayMod(other: Thing, me: Thing): Int {
        if (cwCloser(other, me))
            return 1
        else
            return -1
    }

    fun cwCloser(other: Thing, me: Thing): Boolean {
        // ccw 1
        // dir x = -y;
		// dir y = x;
        val leftX = (other.pos.x + other.dir.x) - (me.pos.x - me.dir.y)
        val leftY = (other.pos.y + other.dir.y) - (me.pos.y + me.dir.x)
        // cw -1
        // dir x = y;
        // dir y = -x;
        val rightX = (other.pos.x + other.dir.x) - (me.pos.x + me.dir.y)
        val rightY = (other.pos.y + other.dir.y) - (me.pos.y - me.dir.x)
        return (leftX * leftX) + (leftY * leftY) > (rightX * rightX) + (rightY * rightY)
    }

    fun  resolveOverlap(a: Thing, b: Thing, delta: Float) {
        a.pos.move(a.dir, -delta * b.viscosity())
        b.pos.move(b.dir, -delta * a.viscosity())
    }

    fun contains(thing: Thing, v: Vec2): Boolean {
        when(thing.shape()) {
            Square -> return vecInsideSquare(thing, v)
            Circle -> return vecInsideCircle(thing, v)
        }
        return noCollision(thing, v)
    }

}
