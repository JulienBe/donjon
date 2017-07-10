package be.julien.donjon.life

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.spatial.Vec2Comp
import be.julien.donjon.util.Rnd
import be.julien.donjon.util.TimeInt
import be.julien.donjon.util.TimeIntComp
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Pool

abstract class Life(val pos: Vec2, var deadCallback: (life: Life) -> Unit): Pool.Poolable {

    internal var energy = initEnergy()

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
        drawer.color(Color.BLACK)
        drawer.drawAbsolute(pos)
    }
}

object LifeComp {
    fun mostBasic(pos: Vec2, deadCallback: (life: Life) -> Unit, right: Float = Rnd.float(), left: Float = Rnd.float(), forward: Float = Rnd.float(), dir: Vec2 = Vec2Comp.rnd()): MostBasic {
        return MostBasic(pos, deadCallback, right, left, forward, dir)
    }
}