package be.julien.donjon.life

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.util.TimeIntComp
import be.julien.donjon.util.Util
import com.badlogic.gdx.graphics.Color

abstract class Life(val pos: Vec2) {

    internal var energy = TimeIntComp.get(100, 1f, -1)

    init {
        energy.setCallback(0, this::noEnergy)
    }

    private fun noEnergy(): Unit {
        Util.out("no Energy")
    }

    open fun act(delta: Float) {
        energy.act()
    }

    fun draw(drawer: Drawer) {
        drawer.color(Color.BLACK)
        drawer.drawAbsolute(pos)
    }
}