package be.julien.donjon.world.shapes

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.util.Rnd
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Circle

class CircleShape internal constructor(radius: Float): Shape() {

    val circle = Circle()

    init {
        circle.setRadius(radius)
    }

    override fun x(): Float = circle.x
    override fun y(): Float = circle.y

    fun set(x: Float, y: Float) {
        this.circle.x = x
        this.circle.y = y
    }

    override fun setPos(x: Float, y: Float) {
        circle.setPosition(x, y)
    }

    override fun b2dShape(): com.badlogic.gdx.physics.box2d.Shape {
        val c = com.badlogic.gdx.physics.box2d.CircleShape()
        c.radius = circle.radius
        return c
    }

    override fun mv(dir: Vec2, delta: Float) {
        circle.x += dir.x * delta
        circle.y += dir.y * delta
    }

    override fun draw(batch: SpriteBatch, drawer: Drawer) {
        batch.draw(drawer.pixel, circle.x, circle.y, circle.radius, circle.radius)
    }

    override fun overlaps(shape: Shape): Boolean {
        when (shape) {
            is RectShape -> return shape.rect.contains(circle)
            is CircleShape -> return shape.circle.overlaps(circle)
            else -> return super.overlaps(shape)
        }
    }
    companion object {
        fun rndPos(width: Float, height: Float): RectShape {
            return RectShape(Rnd.width(), Rnd.height(), width, height)
        }

        fun get(x: Float, y: Float, width: Float, height: Float): RectShape {
            return RectShape(x, y, width, height)
        }
        fun get(width: Float): RectShape {
            return RectShape(-width, -width, width, width)
        }
    }

}