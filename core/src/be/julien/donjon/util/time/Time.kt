package be.julien.donjon.util.time

object Time {
    var time = 0f
    var delta = 0f
    var playerDelta = 0f

    fun act(delta: Float) {
        time += delta
        Time.delta = delta
        playerDelta = delta
    }
}