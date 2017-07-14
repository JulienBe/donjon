package be.julien.donjon.spatial

open class Mover(val pos: Vec2, val dir: Vec2) {
    fun steer(angle: Float) {
        dir.rotate(angle)
    }
    open fun act(delta: Float) {
        pos.x += dir.x * delta
        pos.y += dir.y * delta
    }
}