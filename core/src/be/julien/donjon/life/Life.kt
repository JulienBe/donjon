package be.julien.donjon.life

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.physics.b2d.BoxHelper
import be.julien.donjon.physics.b2d.BoxObject
import be.julien.donjon.spatial.Mover
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.util.Rnd
import be.julien.donjon.util.TimeInt
import be.julien.donjon.util.TimeIntComp
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.utils.Pool

abstract class Life(pos: Vec2, dir: Vec2 = Vec2.getRandWorld(), var deadCallback: (life: Life) -> Unit): Mover(pos, dir), Pool.Poolable, BoxObject {

    internal var energy = initEnergy()
    internal val size = Rectangle(0f, 0f, 1f, 1f)
    internal val body = BoxHelper.createRectangle(BodyDef.BodyType.DynamicBody, size, this, pos, 5f)

    private fun initEnergy(): TimeInt {
        val e = TimeIntComp.get(100, 1f, -1)
        e.setCallback(0, this::noEnergy)
        return e
    }

    override fun reset() {
        energy = initEnergy()
    }

    private fun noEnergy(): Unit {
        deadCallback.invoke(this)
    }

    open fun act(delta: Float) {
        energy.act()
    }

    fun draw(drawer: Drawer) {
        drawer.color(Color.YELLOW)
        drawer.drawAbsolute(body.pos(), size)
    }

    fun collidesWith(any: Any) {
        println("collision life")
        energy.step()
    }

    companion object {
        fun mostBasic(deadCallback: (life: Life) -> Unit, pos: Vec2 = Vec2.getRandWorld(), dir: Vec2 = Vec2.getRandWorld(), right: Float = Rnd.float(), left: Float = Rnd.float(), forward: Float = Rnd.float()): MostBasic {
            return MostBasic(pos, dir, deadCallback, right, left, forward)
        }
    }
}