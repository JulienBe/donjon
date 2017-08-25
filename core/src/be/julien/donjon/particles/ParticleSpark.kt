package be.julien.donjon.particles

import be.julien.donjon.graphics.AssetMan
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.util.spatial.Dimension
import be.julien.donjon.util.Rnd
import be.julien.donjon.util.spatial.Vec2
import be.julien.donjon.world.Collect

class ParticleSpark(x: Float, y: Float, ttl: Int, baseDir: Vec2): Particle(x, y, ttl) {

    val dir = Vec2.get(baseDir.x(), baseDir.y()).nor().scl(speed * (Math.abs(Rnd.gauss() * speedSpread))).rotate(Rnd.gauss(spread))
    val angle = dir.angle()

    override fun draw(drawer: Drawer) {
        drawer.draw(AssetMan.square, x, y, dim.halfWidth, dim.halfHeight, dim.width, dim.height, angle)
    }

    override fun act(): Boolean {
        x += dir.x()
        y += dir.y()
        return super.act()
    }

    companion object {
        val dim = Dimension.get(0.7f, 0.1f)
        val speed = 0.25f
        val speedSpread = 2f
        val spread = 16f
        fun spawn(x: Float, y: Float, dir: Vec2) {
            Collect.particles.add(
                    ParticleSpark(x - dim.halfWidth, y - dim.halfHeight, Math.abs(Rnd.gauss(5f)).toInt(), dir)
            )
        }
    }
}