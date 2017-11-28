package be.julien.donjon.particles

import be.julien.donjon.graphics.AssetMan
import be.julien.donjon.world.Collect
import be.julien.seed.basics.Dimension
import be.julien.seed.graphics.Drawer
import be.julien.seed.utils.Rnd

open class Particle(var x: Float, var y: Float, var ttl: Int) {
    open fun act(): Boolean {
        return ttl-- < 0
    }

    open fun draw(drawer: Drawer) {
        drawer.drawAO(AssetMan.circle, x, y, dim.width, dim.height)
    }

    companion object {
        val dim = Dimension.get(0.3f, 0.3f)
        fun spawn(x: Float, y: Float, energy: Int) {
            Collect.particles.add(
                    Particle(x - dim.halfWidth, y - dim.halfHeight, Rnd.int(energy + 2))
            )
        }
    }
}