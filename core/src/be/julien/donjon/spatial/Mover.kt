package be.julien.donjon.spatial

import be.julien.donjon.things.Thing

abstract class Mover(rect: Rect, val dir: Vec2): Thing(rect) {
    fun steer(angle: Float) {
        dir.rotate(angle)
    }
    override fun act(delta: Float): Boolean {
        rect.x += dir.x * delta
        rect.y += dir.y * delta
        return super.act(delta)
    }
}