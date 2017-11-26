package be.julien.donjon.things.player

import be.julien.donjon.graphics.AssetMan
import be.julien.donjon.graphics.GdxDrawer
import be.julien.donjon.hubs.Hub
import be.julien.donjon.physics.Physics
import be.julien.donjon.things.Energy
import be.julien.donjon.things.life.MostBasic
import be.julien.donjon.world.Collect
import be.julien.seed.Dimension
import be.julien.seed.Thing
import be.julien.seed.Vec2
import be.julien.seed.WallAO
import be.julien.seed.graphics.Color
import be.julien.seed.graphics.Drawer
import be.julien.seed.physics.Mask
import be.julien.seed.physics.shapes.Circle
import be.julien.seed.physics.shapes.Shape
import be.julien.seed.time.Callback
import be.julien.seed.time.PeriodicTimer
import be.julien.seed.time.Time
import kotlin.reflect.KFunction2

class Ship(pos: Vec2): Thing(pos, Vec2.get(0f, 0f), AssetMan.square) {

    var hp = 100
    var canFire = true
    val fireCooldown = 0.3f
    val fireTimer = PeriodicTimer(fireCooldown, Callback.get(1, {canFire = true}))

    override fun mask(): Mask = Mask.Player
    override fun shape(): Shape = Circle
    override fun dimension(): Dimension = dim
    override fun angle(): Float = 0f
    override fun x(): Float = pos.x()
    override fun y(): Float = pos.y()
    override fun img(): Any = AssetMan.circle
    override fun fast(): Boolean = true

    override fun wallFun(): KFunction2<Thing, WallAO, Unit> = Physics::slide

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
            val b = Bullet.get(Vec2.get(centerX(), centerY()), Vec2.get(x - centerX(), y - centerY()), this)
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