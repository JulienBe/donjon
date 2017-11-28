package be.julien.donjon.particles

import be.julien.donjon.world.Collect
import be.julien.seed.basics.Dimension
import be.julien.seed.utils.Rnd

class ParticleMoving(x: Float, y: Float, ttl: Int, var dirX: Float = Rnd.gauss(), var dirY: Float = Rnd.gauss()): Particle(x, y, ttl) {
    override fun act(): Boolean {
        x += dirX
        y += dirY
        return super.act()
    }

    companion object {
        val dim = Dimension.get(0.3f, 0.3f)
        fun spawn(x: Float, y: Float) {
            Collect.particles.add(
                    ParticleMoving(x - dim.halfWidth, y - dim.halfHeight, Rnd.int(30))
            )
        }
    }
}