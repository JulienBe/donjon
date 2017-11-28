package be.julien.donjon.things.player

import be.julien.donjon.graphics.AssetMan
import be.julien.donjon.hubs.Hub
import be.julien.seed.basics.Dimension
import be.julien.seed.basics.Thing
import be.julien.seed.graphics.Color
import be.julien.seed.graphics.Drawer
import be.julien.seed.physics.Mask
import be.julien.seed.physics.Vec2
import be.julien.seed.physics.shapes.Circle
import be.julien.seed.physics.shapes.Shape

class Bullet private constructor(pos: Vec2, dir: Vec2, val owner: Thing) : Thing(pos, dir, AssetMan.square) {

    var bounce = 0

    init {
        dir.nor().scl(speed)
    }

    override fun mask(): Mask = Mask.Bullet
    override fun dimension(): Dimension = dim
    override fun shape(): Shape = Circle
    override fun img(): Any = AssetMan.circle
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
        drawer.color(Color.WHITE)
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