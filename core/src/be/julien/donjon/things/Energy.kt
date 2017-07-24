package be.julien.donjon.things

import be.julien.donjon.physics.Mask
import be.julien.donjon.physics.b2d.BoxBody
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2

class Energy(boxBody: BoxBody, pos: Vec2, dir: Vec2) : Thing(boxBody, pos, dir) {

    var stored = 10
    override fun mask(): Mask = Mask.Energy
    override fun dimension(): Dimension = dimension

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
        val dimension = Dimension.get(1f, 1f)
        fun get(): Energy {
            return Energy(BoxBody.getRect(1f, 1f), Vec2.getRandWorld(), Vec2.get(0f, 0f))
        }
    }
}