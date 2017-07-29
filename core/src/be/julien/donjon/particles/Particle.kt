package be.julien.donjon.particles

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.util.Rnd
import be.julien.donjon.world.Collect

class Particle(var x: Float, var y: Float, var ttl: Int) {
    fun act(): Boolean {
        return ttl-- < 0
    }
    fun draw(drawer: Drawer) {
        drawer.drawAO(drawer.pixel, x, y, dim.width, dim.height)
    }

    companion object {
        val dim = Dimension.get(0.4f, 0.4f)
        fun spawn(x: Float, y: Float) {
            val p = Particle(x - dim.halfWidth, y - dim.halfHeight, 120 + Rnd.int(160))
            Collect.add(p)
        }
    }
}