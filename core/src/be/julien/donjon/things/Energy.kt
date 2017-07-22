package be.julien.donjon.things

import be.julien.donjon.physics.Mask
import be.julien.donjon.world.shapes.RectShape

class Energy(rect: RectShape) : Thing(rect) {

    var stored = 10
    override fun mask(): Mask = Mask.Energy

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
        fun get(): Energy {
            return Energy(RectShape.rndPos(1f, 1f))
        }
    }
}