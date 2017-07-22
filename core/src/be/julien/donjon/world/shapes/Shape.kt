package be.julien.donjon.world.shapes

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.spatial.Vec2
import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class Shape {
    abstract fun draw(batch: SpriteBatch, drawer: Drawer)
    open fun overlaps(shape: Shape): Boolean {
        throw IllegalArgumentException("Shape collisions " + this + " and " + shape)
    }

    abstract fun x(): Float
    abstract fun y(): Float
    abstract fun mv(dir: Vec2, delta: Float)
    abstract fun b2dShape(): com.badlogic.gdx.physics.box2d.Shape
    abstract fun setPos(x: Float, y: Float)
}