package be.julien.donjon.world

import be.julien.donjon.graphics.Drawer
import be.julien.seed.InputHub
import be.julien.donjon.particles.ParticleCreator
import be.julien.donjon.things.player.Ship
import be.julien.donjon.things.player.ShipControl
import be.julien.donjon.util.spatial.Vec2
import be.julien.donjon.things.Energy
import be.julien.donjon.things.Thing
import be.julien.donjon.things.WallAO
import be.julien.donjon.things.life.Life
import be.julien.donjon.util.time.Callback
import be.julien.donjon.util.time.PeriodicTimer
import be.julien.donjon.util.time.Time

class World(input: InputHub) {

    private val particleCreator = ParticleCreator()
    private val collection = Collect()
    private var debug = false
    private val addEnergyTimer = PeriodicTimer(0.5f, Callback(1, this::addEnergy))
    private val ship = Ship(Vec2.get(5f, 5f))
    private val shipControl = ShipControl(ship, input)

    init {
        val left = WallAO(0f, 0f, WallAO.width, Drawer.screenHeight)
        val right = WallAO(Drawer.screenWidth - WallAO.width, 0f, WallAO.width, Drawer.screenHeight)
        val top = WallAO(0f, Drawer.screenHeight - WallAO.width, Drawer.screenWidth, WallAO.width)
        val bottom = WallAO(0f, 0f, Drawer.screenWidth, WallAO.width)
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

    fun draw(drawer: Drawer) {
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
                if (i < max)
                    return i
                else
                    return max
            return 0f
        }
        fun trimX(x: Float): Float {
            return trim(x, Drawer.screenWidth - 1f)
        }
        fun trimY(y: Float): Float {
            return trim(y, Drawer.screenHeight - 1f)
        }
    }

}