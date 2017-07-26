package be.julien.donjon.things

import be.julien.donjon.GdxArr
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.physics.Mask
import be.julien.donjon.physics.b2d.BoxBody
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.sensors.SquareSensor

abstract class Thing(val pos: Vec2, val dir: Vec2, val body: BoxBody) {

    init {
        body.setRef(this)
    }
    val sensors = GdxArr<SquareSensor>()
    internal var dead = false

    /**
     * It means that it will act one more frame after it's dead as the dead flag is probably stat after it has acted and will be only checked next frame, after it has acted again.
     */
    open fun act(delta: Float): Boolean {
        pos.move(dir, delta)
        body.setPos(pos.x, pos.y)
        return dead
    }

    open fun draw(drawer: Drawer): Unit {
        drawer.drawAbsolute(this)
    }

    fun steer(angle: Float, delta: Float) {
        dir.rotate(angle * delta)
    }

    fun hw(): Float = dimension().halfWidth
    fun w(): Float = dimension().width
    fun hh(): Float = dimension().halfHeight
    fun h(): Float = dimension().height

    open fun angle(): Float = 0f
    open fun die(): Unit {
        dead = true
        sensors.forEach { it.dead = true }
    }

    fun x(): Float = body.x()
    fun y(): Float = body.y()

    abstract fun collidesWith(thing: Thing)
    abstract fun mask(): Mask
    abstract fun dimension(): Dimension

}