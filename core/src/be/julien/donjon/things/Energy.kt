package be.julien.donjon.things

import be.julien.donjon.graphics.AssetMan
import be.julien.donjon.GdxDrawer
import be.julien.seed.basics.Dimension
import be.julien.seed.basics.Thing
import be.julien.seed.graphics.Drawer
import be.julien.seed.physics.Mask
import be.julien.seed.physics.Vec2
import be.julien.seed.physics.shapes.Shape
import be.julien.seed.physics.shapes.SquareAO
import be.julien.seed.utils.Rnd

class Energy(pos: Vec2, dir: Vec2) : Thing(pos, dir) {

    private var thisAngle = Rnd.float(360f)
    private var stored = 40
    private var speed = determineSpeed()
    override val angle: Float
        get() = thisAngle
    override val mask: Mask
        get() = Mask.Energy
    override val dimension: Dimension
        get() = dim
    override val shape: Shape
        get() = SquareAO
    override val img: Any
        get() = AssetMan.square

    private fun determineSpeed() = stored * 8f

    override fun act(delta: Float): Boolean {
        if (stored <= 0)
            die()
        thisAngle += delta * speed
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

    override fun viscosity(a: Thing): Float {
        return 0.5f
    }

    companion object {
        val dim = Dimension.get(1f, 1f)
        fun get(): Energy {
            return Energy(Vec2.getRandWorld(GdxDrawer.screenWidth, GdxDrawer.screenHeight), Vec2.get(0f, 0f))
        }
    }

}