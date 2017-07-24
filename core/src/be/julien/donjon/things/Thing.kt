package be.julien.donjon.things

import be.julien.donjon.GdxArr
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.physics.Mask
import be.julien.donjon.physics.b2d.BoxBody
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.sensors.SquareSensor

abstract class Thing(val boxBody: BoxBody, val pos: Vec2, val dir: Vec2) {
    val sensors = GdxArr<SquareSensor>()

    internal var dead = false

    /**
     * It means that it will act one more frame after it's dead as the dead flag is probably stat after it has acted and will be only checked next frame, after it has acted again.
     */
    open fun act(delta: Float): Boolean {
        pos.move(dir, delta)
        boxBody.setPos(pos.x, pos.y)
        return dead
    }

    open fun draw(drawer: Drawer): Unit {
    }

    fun steer(angle: Float, delta: Float) {
        dir.rotate(angle * delta)
    }

    open fun angle(): Float = 0f
    open fun die(): Unit {
        dead = true
        sensors.forEach { it.dead = true }
    }

    fun x(): Float = boxBody.x()
    fun y(): Float = boxBody.y()

    abstract fun collidesWith(thing: Thing)
    abstract fun mask(): Mask
    abstract fun dimension(): Dimension

}