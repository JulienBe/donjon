package be.julien.donjon.physics

import be.julien.donjon.physics.shapes.Circle
import be.julien.donjon.physics.shapes.SquareAO
import be.julien.donjon.util.spatial.Vec2
import be.julien.donjon.things.Thing
import be.julien.donjon.util.Util

object Physics {

    val rollBackSteps = 8
    val rollBackPrecision = (1f / rollBackSteps)

    fun angle(to: Thing, from: Thing): Float = angle(to.centerX(), to.centerY(), from.centerX(), from.centerY())

    fun angle(xTo: Float, yTo: Float, xFrom: Float, yFrom: Float): Float = Vec2.tmp.set(xTo - xFrom, yTo - yFrom).angle()

    fun checkCollision(a: Thing, b: Thing): Boolean {
        when(a.shape()) {
            SquareAO -> return squareCheck(a, b)
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
            SquareAO -> return squareCircle(b, circle)
        }
        return noCollision(circle, b)
    }

    private fun squareCheck(square: Thing, b: Thing): Boolean {
        when(b.shape()) {
            SquareAO -> return squareSquare(square, b)
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
        if (distY <= square.hh())
            return true
        val dx = distX - square.hw()
        val dy = distY - square.hh()
        return (dx * dx + dy * dy) <= (circle.hw() * circle.hw())
    }

    private fun vecInsideSquare(t: Thing, v: Vec2): Boolean {
        return t.x() < v.x() &&
                t.x() + t.w() > v.x() &&
                t.y() < v.y() &&
                t.h() + t.y() > v.y()
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
        val leftX = (other.pos.x() + other.dir.x()) - (me.pos.x() - me.dir.y())
        val leftY = (other.pos.y() + other.dir.y()) - (me.pos.y() + me.dir.x())
        // cw -1
        // dir x = y;
        // dir y = -x;
        val rightX = (other.pos.x() + other.dir.x()) - (me.pos.x() + me.dir.y())
        val rightY = (other.pos.y() + other.dir.y()) - (me.pos.y() - me.dir.x())
        return (leftX * leftX) + (leftY * leftY) > (rightX * rightX) + (rightY * rightY)
    }

    fun resolveOverlap(a: Thing, b: Thing) {
        if (a.fast()) {
            var cpt = 1
            while (cpt <= rollBackSteps) {
                a.pos.rollback(b.viscosity(a) * rollBackPrecision * cpt)
                cpt++
                if (checkCollision(a, b)) {
                    a.pos.rollback(-b.viscosity(a) * rollBackPrecision * cpt)
                } else {
                    break
                }
            }
        } else {
            a.pos.rollback(b.viscosity(a))
        }
    }

    fun contains(thing: Thing, v: Vec2): Boolean {
        when(thing.shape()) {
            SquareAO -> return vecInsideSquare(thing, v)
            Circle -> return vecInsideCircle(thing, v)
        }
        return noCollision(thing, v)
    }

    fun dirCenter(other: Thing, me: Thing): Vec2 {
        return Vec2.get(other.centerX() - me.centerX(), other.centerY() - me.centerY())
    }

    fun intersectLines(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float, x4: Float, y4: Float): Boolean {
        return lineIntersection(x1, y1, x2, y2, x3, y3, x4, y4).equals(Vec2.tmp)
    }

    fun lineIntersection(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float, x4: Float, y4: Float): Vec2 {
        val s10_x = x2 - x1
        val s10_y = y2 - y1
        val s32_x = x4 - x3
        val s32_y = y4 - y3

        val denom = s10_x * s32_y - s32_x * s10_y
        if (denom == 0f)
            return Vec2.zero
        val denomPositive = denom > 0f

        val s02_x = x1 - x3
        val s02_y = y1 - y3
        val s_numer = s10_x * s02_y - s10_y * s02_x
        if ((s_numer < 0) == denomPositive)
            return Vec2.zero

        val t_numer = s32_x * s02_y - s32_y * s02_x
        if ((t_numer < 0) == denomPositive)
            return Vec2.zero

        if (((s_numer > denom) == denomPositive) || ((t_numer > denom) == denomPositive))
            return Vec2.zero

        // Collision detected
        val t = t_numer / denom
        return Vec2.tmp.set(x1 + (t * s10_x), y1 + (t * s10_y)) as Vec2
    }

    fun  distSq(t1: Thing, t2: Thing): Float {
        val distX = t1.centerX() - t2.centerX()
        val distY = t1.centerY() - t2.centerY()
        return (distX * distX) - (distY * distY)
    }

}
