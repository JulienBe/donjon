package be.julien.donjon.world

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.things.Energy
import be.julien.donjon.things.Thing
import be.julien.donjon.things.Wall
import be.julien.donjon.things.life.Life
import be.julien.donjon.util.Time

class World {

    internal val collection = Collect()
    internal var debug = false

    init {
        val left = Wall(0f, 0f, Wall.width, Drawer.screenHeight)
        val right = Wall(Drawer.screenWidth - Wall.width, 0f, Wall.width, Drawer.screenHeight)
        val top = Wall(0f, Drawer.screenHeight - Wall.width, Drawer.screenWidth, Wall.width)
        val bottom = Wall(0f, 0f, Drawer.screenWidth, Wall.width)
        collection.add(left, right, top, bottom)
    }

    fun act(delta: Float) {
        collection.check(delta)
        collection.act(delta)
        Time.act(delta)
        if (collection.nbEnergy() < energy)
            collection.add(Energy.get())
    }

    fun draw(drawer: Drawer) {
        collection.draw(drawer)
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