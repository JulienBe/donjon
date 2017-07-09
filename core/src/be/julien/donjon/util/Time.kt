package be.julien.donjon.util

object Time {
    var time = 0f

    fun act(delta: Float) {
        time += delta
    }
}