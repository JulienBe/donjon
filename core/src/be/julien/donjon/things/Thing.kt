package be.julien.donjon.things

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.spatial.Rect

abstract class Thing(val rect: Rect) {
    var dead = false
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
}