package be.julien.donjon.things

import be.julien.donjon.spatial.Rect

class Wall(rect: Rect): Thing(rect) {
    override fun collidesWith(thing: Thing) {
    }

    companion object {
        val width = 2f
    }
}