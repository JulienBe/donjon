package be.julien.donjon.things

import be.julien.donjon.physics.Mask
import be.julien.donjon.physics.shapes.Shape
import be.julien.donjon.physics.shapes.Square
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2

class Wall(x: Float, y: Float, width: Float, height: Float,
           val dim: Dimension = Dimension.get(width, height)): Thing(Vec2.get(x, y), Vec2.get(0f, 0f)) {

    override fun shape(): Shape = Square
    override fun dimension(): Dimension = dim
    override fun mask(): Mask = Mask.Wall

    override fun collidesWith(thing: Thing) {
    }

    companion object {
        val width = 2f
    }
}