package be.julien.donjon.things

import be.julien.donjon.graphics.AssetMan
import be.julien.donjon.graphics.GdxDrawer
import be.julien.seed.Dimension
import be.julien.seed.Rnd
import be.julien.seed.Thing
import be.julien.seed.Vec2
import be.julien.seed.graphics.Drawer
import be.julien.seed.physics.Mask
import be.julien.seed.physics.shapes.Shape
import be.julien.seed.physics.shapes.SquareAO

class Energy(pos: Vec2, dir: Vec2) : Thing(pos, dir, AssetMan.square) {

    private var angle = Rnd.float(360f)
    private var stored = 40
    private var speed = determineSpeed()

    private fun determineSpeed() = stored * 8f
    override fun mask(): Mask = Mask.Energy
    override fun dimension(): Dimension = dim
    override fun shape(): Shape = SquareAO
    override fun img(): Any = AssetMan.square

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
        return stored
    }

    fun stealEnergy(i: Int): Int {
        if (stored > i) {
            stored -= i
            speed = determineSpeed()
            return i
        } else {
            stored = 0
            return stored
        }
    }

    companion object {
        val dim = Dimension.get(1f, 1f)
        fun get(): Energy {
            return Energy(Vec2.getRandWorld(GdxDrawer.screenWidth, GdxDrawer.screenHeight), Vec2.get(0f, 0f))
        }
    }

    override fun viscosity(a: Thing): Float {
        return 0.5f
    }

    override fun angle(): Float = angle
}