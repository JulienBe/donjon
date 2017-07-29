package be.julien.donjon.things

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.physics.Mask
import be.julien.donjon.physics.shapes.Shape
import be.julien.donjon.physics.shapes.Square
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.util.Rnd

class Energy(pos: Vec2, dir: Vec2) : Thing(pos, dir) {

    var angle = Rnd.float(360f)
    var stored = 40
    var speed = determineSpeed()

    private fun determineSpeed() = stored * 8f
    override fun mask(): Mask = Mask.Energy
    override fun dimension(): Dimension = dim
    override fun shape(): Shape = Square

    override fun act(delta: Float): Boolean {
        if (stored <= 0)
            die()
        angle += delta * speed
        return super.act(delta)
    }

    override fun draw(drawer: Drawer) {
        drawer.drawNAO(this)
    }

    fun getEnergy(): Int {
        stored--
        speed = determineSpeed()
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

    override fun angle(): Float = angle
}