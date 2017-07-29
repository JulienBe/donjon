package be.julien.donjon.things

import be.julien.donjon.GdxArr
import be.julien.donjon.graphics.Drawable
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.physics.Mask
import be.julien.donjon.physics.shapes.Shape
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.sensors.SquareSensor
import com.badlogic.gdx.graphics.g2d.TextureRegion

abstract class Thing(val pos: Vec2, val dir: Vec2) : Drawable {

    val sensors = GdxArr<SquareSensor>()
    internal var dead = false

    /**
     * It means that it will act one more frame after it's dead as the dead flag is probably stat after it has acted and will be only checked next frame, after it has acted again.
     */
    open fun act(delta: Float): Boolean {
        pos.move(dir, delta)
        return dead
    }

    open fun draw(drawer: Drawer): Unit {
        drawer.drawAO(this)
    }

    fun steer(angle: Float, delta: Float) {
        dir.rotate(angle * delta)
    }

    open fun die(): Unit {
        dead = true
        sensors.forEach { it.dead = true }
    }

    override fun x(): Float = pos.x
    override fun y(): Float = pos.y
    override fun hw(): Float = dimension().halfWidth
    override fun w(): Float = dimension().width
    override fun hh(): Float = dimension().halfHeight
    override fun h(): Float = dimension().height
    override fun angle(): Float = 0f
    override fun tr(): TextureRegion {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    abstract fun collidesWith(thing: Thing)
    abstract fun mask(): Mask
    abstract fun dimension(): Dimension
    abstract fun shape(): Shape

}