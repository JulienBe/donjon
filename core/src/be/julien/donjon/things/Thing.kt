package be.julien.donjon.things

import be.julien.donjon.GdxArr
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.physics.Mask
import be.julien.donjon.physics.shapes.Shape
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.sensors.SquareSensor

abstract class Thing(val pos: Vec2, val dir: Vec2) {

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
//        drawer.drawAbsolute(this)
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

    fun x(): Float = pos.x
    fun y(): Float = pos.y

    abstract fun collidesWith(thing: Thing)
    abstract fun mask(): Mask
    abstract fun dimension(): Dimension
    abstract fun shape(): Shape

}