package be.julien.donjon.world

import be.julien.donjon.GdxArr
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.physics.Collider
import be.julien.donjon.spatial.Rect
import be.julien.donjon.things.Thing
import be.julien.donjon.things.Wall
import be.julien.donjon.things.life.Life
import be.julien.donjon.util.Time

class World {

    internal val things = GdxArr<Thing>()
    internal val sensors = GdxArr<Thing>()
    internal var debug = false

    init {
        val left = Wall(Rect.get(0f, 0f, Wall.width, Drawer.screenHeight))
        val right = Wall(Rect.get(Drawer.screenWidth - Wall.width, 0f, Wall.width, Drawer.screenHeight))
        val top = Wall(Rect.get(0f, Drawer.screenHeight - Wall.width, Drawer.screenWidth, Wall.width))
        val bottom = Wall(Rect.get(0f, 0f, Drawer.screenWidth, Wall.width))
        things.add(left)
        things.add(right)
        things.add(top)
        things.add(bottom)
    }

    fun act(delta: Float) {
        things.forEach {
            if (it.act(delta))
                deadThing(it)
        }
        sensors.forEach { it.act(delta) }
        Collider.check(things)
        Collider.sensors(sensors, things)
        Time.act(delta)
    }

    fun draw(drawer: Drawer) {
        things.forEach { it.draw(drawer) }
        if (debug)
            sensors.forEach { it.draw(drawer) }
    }

    fun spawn() {
        for (i in 1 .. initLife)
            newLife()
    }

    private fun newLife() {
        val l = Life.mostBasic()
        things.add(l)
        sensors.addAll(l.sensors)
    }

    fun deadThing(thing: Thing) {
        things.removeValue(thing, true)
        sensors.removeAll(thing.sensors, true)
    }

    fun debug() {
        debug = !debug
    }

    companion object {
        val initLife = 10
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