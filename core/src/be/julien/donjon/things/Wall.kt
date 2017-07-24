package be.julien.donjon.things

import be.julien.donjon.physics.Mask
import be.julien.donjon.physics.b2d.BoxBody
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2

class Wall(x: Float, y: Float, width: Float, height: Float): Thing(BoxBody.getRect(x, y, width, height), Vec2.get(x, y), Vec2.get(0f, 0f)) {

    override fun dimension(): Dimension = dimension
    override fun mask(): Mask = Mask.Wall

    override fun collidesWith(thing: Thing) {
    }

    companion object {
        val dimension = Dimension.get(2f, 2f)
        val width = 2f
    }
}