package be.julien.donjon.particles

import be.julien.donjon.graphics.AssetMan
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.util.Rnd
import be.julien.donjon.world.Collect

class Particle(var x: Float, var y: Float, var ttl: Int) {
    fun act(): Boolean {
        return ttl-- < 0
    }

    fun draw(drawer: Drawer) {
        drawer.drawAO(AssetMan.circle, x, y, dim.width, dim.height)
    }

    companion object {
        val dim = Dimension.get(0.3f, 0.3f)
        fun spawn(x: Float, y: Float, energy: Int) {
            Collect.add(
                    Particle(x - dim.halfWidth, y - dim.halfHeight, Rnd.int(energy + 2))
            )
        }
    }
}