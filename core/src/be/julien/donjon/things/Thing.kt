package be.julien.donjon.things

import be.julien.donjon.GdxArr
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.physics.Mask
import be.julien.donjon.spatial.Rect

abstract class Thing(val rect: Rect) {
    val sensors = GdxArr<Sensor>()

    internal var dead = false

    /**
     * It means that it will act one more frame after it's dead as the dead flag is probably stat after it has acted and will be only checked next frame, after it has acted again.
     */
    open fun act(delta: Float): Boolean {
        return dead
    }
    open fun draw(drawer: Drawer): Unit {
        drawer.drawAbsolute(rect)
    }
    abstract fun collidesWith(thing: Thing)
    open fun angle(): Float = 0f
    abstract fun mask(): Mask
    open fun die(): Unit {
        dead = true
        sensors.forEach { it.dead = true }
    }

}