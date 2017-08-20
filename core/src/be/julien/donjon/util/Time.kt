package be.julien.donjon.util

object Time {
    var time = 0f
    var delta = 0f

    fun act(delta: Float) {
        time += delta
        this.delta = delta
    }
}