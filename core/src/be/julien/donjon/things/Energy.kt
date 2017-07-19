package be.julien.donjon.things

import be.julien.donjon.physics.Mask
import be.julien.donjon.spatial.Rect

class Energy(rect: Rect) : Thing(rect) {

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
            return Energy(Rect.rndPos(1f, 1f))
        }
    }
}