package be.julien.donjon.world.shapes

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.util.Rnd
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.PolygonShape

class RectShape internal constructor(x: Float, y: Float, width: Float, height: Float): Shape() {

    val rect = Rectangle(x, y, width, height)

    override fun x(): Float = rect.x + rect.width / 2f
    override fun y(): Float = rect.y + rect.height / 2f

    fun set(x: Float, y: Float) {
        this.rect.x = x
        this.rect.y = y
    }

    override fun setPos(x: Float, y: Float) {
        rect.setPosition(x, y)
    }

    override fun b2dShape(): com.badlogic.gdx.physics.box2d.Shape {
        val polygon = PolygonShape()
        val center = Vec2.get(rect.x + rect.width * 0.5f, rect.y + rect.height * 0.5f)
        polygon.setAsBox((rect.width * 0.5f), (rect.height * 0.5f), center, 0.0f)
        return polygon
    }

    override fun mv(dir: Vec2, delta: Float) {
        rect.x += dir.x * delta
        rect.y += dir.y * delta
    }

    override fun draw(batch: SpriteBatch, drawer: Drawer) {
        batch.draw(drawer.pixel, rect.x, rect.y, rect.width, rect.height)
    }

    override fun overlaps(shape: Shape): Boolean {
        when (shape) {
            is RectShape -> return shape.rect.overlaps(rect)
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