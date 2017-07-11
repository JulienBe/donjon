package be.julien.donjon.world

import be.julien.donjon.GdxArr
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.util.Util
import be.julien.donjon.life.Life
import be.julien.donjon.life.LifeComp
import be.julien.donjon.physics.BoxWorld
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2Comp
import be.julien.donjon.spatial.PosPool
import be.julien.donjon.util.Time

class World {

    internal val lifeforms = GdxArr<Life>()

    fun act(delta: Float) {
        lifeforms.forEach { l -> l.act(delta) }
        Time.act(delta)
    }

    fun draw(drawer: Drawer) {
        lifeforms.forEach { l -> l.draw(drawer) }
    }

    fun spawn() {
        BoxWorld.init()
        for (i in 1 .. WorldWiz.initLife)
            lifeforms.add(newLife())
    }

    private fun newLife(): Life {
        return LifeComp.mostBasic(Vec2Comp.getRandWorld(), this::deadLife)
    }

    fun deadLife(life: Life) {
        lifeforms.removeValue(life, true)
    }
}

object WorldWiz {
    val dim = Dimension(40, 40)
    val initLife = 10
    fun trim(i: Int, max: Int): Int {
        if (i > 0)
            if (i < max)
                return i
            else
                return max
        return 0
    }
    fun trimX(x: Int): Int {
        return trim(x, dim.col - 1)
    }
    fun trimY(y: Int): Int {
        return trim(y, dim.row - 1)
    }
}