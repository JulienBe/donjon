package be.julien.donjon.world

import be.julien.donjon.GdxDrawer
import be.julien.donjon.particles.ParticleCreator
import be.julien.donjon.things.WallAOBasic
import be.julien.donjon.things.player.Ship
import be.julien.donjon.things.player.ShipControl
import be.julien.donjon.things.life.Life
import be.julien.seed.basics.Thing
import be.julien.seed.basics.WallAO
import be.julien.seed.basics.WallSide
import be.julien.seed.physics.Vec2
import be.julien.seed.time.Callback
import be.julien.seed.time.PeriodicTimer
import be.julien.seed.time.Time
import be.julien.seed.utils.InputHub

class World(input: InputHub) {

    private val particleCreator = ParticleCreator()
    private val collection = Collect()
    private var debug = false
    private val addEnergyTimer = PeriodicTimer(0.5f, Callback(1, this::addEnergy))
    private val ship = Ship(Vec2.get(5f, 5f))
    private val shipControl = ShipControl(ship, input)

    init {
        val left = WallAOBasic(0f, 0f, WallAO.width, GdxDrawer.screenHeight, WallSide.right)
        val right = WallAOBasic(GdxDrawer.screenWidth - WallAO.width, 0f, WallAO.width, GdxDrawer.screenHeight, WallSide.left)
        val top = WallAOBasic(0f, GdxDrawer.screenHeight - WallAO.width, GdxDrawer.screenWidth, WallAO.width, WallSide.bottom)
        val bottom = WallAOBasic(0f, 0f, GdxDrawer.screenWidth, WallAO.width, WallSide.top)
        collection.add(left, right, top, bottom)
        collection.add(ship)
    }

    fun act(delta: Float) {
        collection.act(delta)
        collection.check()
        addEnergyTimer.act()
        Time.act(delta)
    }

    fun addEnergy() {
//        collection.add(Energy.get())
    }

    fun draw(drawer: GdxDrawer) {
        collection.draw(drawer)
//        collection.debug(drawer)
    }

    fun spawn() {
        for (i in 1 .. initLife)
            newLife()
    }

    private fun newLife() {
        collection.add(Life.mostBasic(collection.walls()))
    }

    fun deadThing(thing: Thing) {
        collection.remove(thing)
    }

    fun debug() {
        debug = !debug
    }

    companion object {
        val initLife = 1
        val energy = 40
        fun trim(i: Float, max: Float): Float {
            if (i > 0)
                return if (i < max)
                    i
                else
                    max
            return 0f
        }
        fun trimX(x: Float): Float = trim(x, GdxDrawer.screenWidth - 1f)
        fun trimY(y: Float): Float = trim(y, GdxDrawer.screenHeight - 1f)
    }

}