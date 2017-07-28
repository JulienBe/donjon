package be.julien.donjon.things

import be.julien.donjon.physics.Mask
import be.julien.donjon.physics.shapes.Shape
import be.julien.donjon.physics.shapes.Square
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2

class Energy(pos: Vec2, dir: Vec2) : Thing(pos, dir) {

    var stored = 10
    override fun mask(): Mask = Mask.Energy
    override fun dimension(): Dimension = dim
    override fun shape(): Shape = Square

    override fun act(delta: Float): Boolean {
        if (stored <= 0)
            die()
        return super.act(delta)
    }

    fun getEnergy(): Int {
        stored--
        return 1
    }

    override fun collidesWith(thing: Thing) {
    }

    companion object {
        val dim = Dimension.get(1f, 1f)
        fun get(): Energy {
            return Energy(Vec2.getRandWorld(), Vec2.get(0f, 0f))
        }
    }
}