package be.julien.donjon.spatial

open class Mover(val rect: Rect, val dir: Vec2) {
    fun steer(angle: Float) {
        dir.rotate(angle)
    }
    open fun act(delta: Float) {
        rect.x += dir.x * delta
        rect.y += dir.y * delta
    }
}