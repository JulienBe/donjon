package be.julien.donjon.world

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.things.Energy
import be.julien.donjon.things.Thing
import be.julien.donjon.things.WallAO
import be.julien.donjon.things.life.Life
import be.julien.donjon.util.Callback
import be.julien.donjon.util.PeriodicTimer
import be.julien.donjon.util.Time

class World {

    private val collection = Collect()
    private var debug = false
    private val addEnergyTimer = PeriodicTimer(0.01f, Callback(1, this::addEnergy))

    init {
        val left = WallAO(0f, 0f, WallAO.width, Drawer.screenHeight)
        val right = WallAO(Drawer.screenWidth - WallAO.width, 0f, WallAO.width, Drawer.screenHeight)
        val top = WallAO(0f, Drawer.screenHeight - WallAO.width, Drawer.screenWidth, WallAO.width)
        val bottom = WallAO(0f, 0f, Drawer.screenWidth, WallAO.width)
        collection.add(left, right, top, bottom)
    }

    fun act(delta: Float) {
        collection.check(delta)
        collection.act(delta)
        addEnergyTimer.act()
        Time.act(delta)
    }

    fun addEnergy() {
        collection.add(Energy.get())
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