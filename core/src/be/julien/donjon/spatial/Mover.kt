package be.julien.donjon.spatial

import be.julien.donjon.things.Thing
import be.julien.donjon.world.shapes.RectShape

abstract class Mover(rect: RectShape, val dir: Vec2): Thing(rect) {
    fun steer(angle: Float, delta: Float) {
        dir.rotate(angle * delta)
    }
    override fun act(delta: Float): Boolean {
        shape.mv(dir, delta)
        return super.act(delta)
    }
}