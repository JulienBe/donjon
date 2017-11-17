package be.julien.donjon.things.player

import be.julien.donjon.graphics.AssetMan
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.hubs.Hub
import be.julien.donjon.physics.Mask
import be.julien.donjon.physics.shapes.Circle
import be.julien.donjon.physics.shapes.Shape
import be.julien.donjon.things.Thing
import be.julien.donjon.util.spatial.Dimension
import be.julien.donjon.util.spatial.Vec2
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion

class Bullet private constructor(pos: Vec2, dir: Vec2, val owner: Thing) : Thing(pos, dir) {

    var bounce = 0

    init {
        dir.nor().scl(speed)
    }

    override fun mask(): Mask = Mask.Bullet
    override fun dimension(): Dimension = dim
    override fun shape(): Shape = Circle
    override fun tr(): TextureRegion = AssetMan.circle
    override fun viscosity(a: Thing): Float = 0.5f

    override fun collidesWith(thing: Thing) {
        bounce++
        super.collidesWith(thing)
    }

    override fun die() {
        Hub.bulletRemoval(this)
        super.die()
    }

    override fun act(delta: Float): Boolean {
        super.act(delta)
        return bounce >= maxBounce
    }

    override fun draw(drawer: Drawer) {
        drawer.color(Color.CYAN)
        super.draw(drawer)
    }

    companion object {
        val dim = Dimension.get(1f, 1f)
        val speed = 100f
        val maxBounce = 2

        fun get(pos: Vec2, dir: Vec2, owner: Thing): Bullet {
            val b = Bullet(pos, dir, owner)
            b.pos.set(pos.x() - dim.halfWidth, pos.y() - dim.halfHeight)
            return b
        }
    }
}