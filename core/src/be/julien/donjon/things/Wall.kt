package be.julien.donjon.things

import be.julien.donjon.physics.Mask
import be.julien.donjon.world.shapes.Rect

class Wall(rect: Rect): Thing(rect) {
    override fun mask(): Mask = Mask.Wall

    override fun collidesWith(thing: Thing) {
    }

    companion object {
        val width = 2f
    }
}