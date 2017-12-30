package be.julien.donjon.things.player

import be.julien.donjon.graphics.AssetMan
import be.julien.donjon.hubs.Hub
import be.julien.donjon.things.Energy
import be.julien.donjon.things.life.MostBasic
import be.julien.donjon.world.Collect
import be.julien.seed.basics.Dimension
import be.julien.seed.basics.Thing
import be.julien.seed.basics.WallAO
import be.julien.seed.graphics.Color
import be.julien.seed.graphics.Drawer
import be.julien.seed.physics.Mask
import be.julien.seed.physics.Physics
import be.julien.seed.physics.Vec2
import be.julien.seed.physics.shapes.Circle
import be.julien.seed.physics.shapes.Shape
import be.julien.seed.time.Callback
import be.julien.seed.time.PeriodicTimer
import be.julien.seed.time.Time
import kotlin.reflect.KFunction2

class Ship(pos: Vec2): Thing(pos, Vec2.get(0f, 0f)) {

    var hp = 100
    var canFire = true
    val fireCooldown = 0.3f
    val fireTimer = PeriodicTimer(fireCooldown, Callback.get(1, {canFire = true}))

    override val mask: Mask
        get() = Mask.Player
    override val shape: Shape
        get() = Circle
    override val dimension: Dimension
        get() = dim
    override val angle: Float
        get() = 0f
    override val img: Any
        get() = AssetMan.circle
    override val fast: Boolean
        get() = true

    override fun onWallHit(): KFunction2<Thing, WallAO, Unit> = Physics::setOnWall

    override fun collidesWith(thing: Thing) {
        when (thing) {
            is Energy -> addHp(thing.stealEnergy(5))
            is MostBasic -> pushLife(thing)
        }
    }

    override fun act(delta: Float): Boolean {
        fireTimer.act()
        return super.act(delta)
    }

    override fun draw(drawer: Drawer) {
        drawer.color(Color.WHITE)
        super.draw(drawer)
    }

    private fun pushLife(thing: MostBasic) {
        thing.pos.move(dir, Time.playerDelta)
    }

    fun addHp(stealEnergy: Int) {
        hp += stealEnergy
    }

    fun left() {
        dir.setX(-speed)
        dir.nor().scl(speed)
    }
    fun right() {
        dir.setX(speed)
        dir.nor().scl(speed)
    }
    fun up() {
        dir.setY(speed)
        dir.nor().scl(speed)
    }
    fun down() {
        dir.setY(-speed)
        dir.nor().scl(speed)
    }
    fun notLateral() {
        dir.setX(0f)
    }
    fun notVertical() {
        dir.setY(0f)
    }

    fun click(x: Float, y: Float) {
        if (canFire) {
            val b = Bullet.get(Vec2.get(centerX, centerY), Vec2.get(x - centerX, y - centerY), this)
            Hub.bulletCreation(b)
            Collect.thingsToAdd.add(b)
            canFire = false
        }
    }

    companion object {
        val dim = Dimension.get(3f, 3f)
        val speed = 100f
    }
}