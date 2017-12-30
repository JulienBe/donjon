package be.julien.donjon.world

import be.julien.donjon.GdxArr
import be.julien.donjon.GdxDrawer
import be.julien.donjon.particles.Particle
import be.julien.seed.basics.Sensor
import be.julien.seed.basics.Thing
import be.julien.seed.physics.Mask
import be.julien.seed.physics.Physics

class Collect {
    private val stuff = HashMap<Mask, GdxArr<Thing>>()
    private val removedThings = GdxArr<Thing>()
    private val removedParticles = GdxArr<Particle>()
    private var keys: MutableSet<Mask>

    init {
        stuff.put(Mask.Life, GdxArr())
        stuff.put(Mask.Sensor, GdxArr())
        stuff.put(Mask.Wall, GdxArr())
        stuff.put(Mask.Energy, GdxArr())
        stuff.put(Mask.Bullet, GdxArr())
        stuff.put(Mask.Player, GdxArr())
        keys = stuff.keys
    }

    fun walls(): GdxArr<Thing> = stuff[Mask.Wall]!!

    fun add(vararg things: Thing) {
        things.forEach {
            stuff[it.mask]!!.add(it)
            it.sensors.forEach {
                sensor: Sensor -> stuff[Mask.Sensor]!!.add(sensor)
            }
        }
    }

    fun remove(vararg things: Thing) {
        things.forEach {
            stuff[it.mask]!!.removeValue(it, true)
        }
    }

    fun act(delta: Float) {
        particles.forEach {
            if (it.act())
                removedParticles.add(it)
        }
        particles.removeAll(removedParticles, true)
        removedParticles.clear()
        thingsToAdd.forEach {
            add(it)
        }
        thingsToAdd.clear()
        stuff.forEach { mask, gdxArr ->
            removedThings.clear()
            for (i in 0.until(gdxArr.size)) {
                val thing = gdxArr.get(i)
                if (thing.act(delta))
                    removedThings.add(thing)
            }
            gdxArr.removeAll(removedThings, true)
        }
    }

    fun draw(drawer: GdxDrawer) {
        stuff.forEach { mask, gdxArr ->
            gdxArr.forEach { it.draw(drawer) }
        }
        particles.forEach { it.draw(drawer) }
    }

    fun nbEnergy(): Int = stuff[Mask.Energy]!!.size

    fun check() {
        for (i in 0.until(keys.size))
            checkMask(i)
    }

    private fun checkMask(i: Int) {
        val maskA = keys.elementAt(i)
        for (j in i.until(keys.size)) {
            val maskB = keys.elementAt(j)
            // TODO avoid double
            if (maskA.collidesWith(maskB)) {
                checkCollision(maskA, maskB)
            }
            if (maskB.collidesWith(maskA)) {
                checkCollision(maskB, maskA)
            }
        }
    }

    private fun checkCollision(from: Mask, to: Mask) {
        val arrayFrom = stuff[from]
        val arrayTo = stuff[to]
        arrayFrom!!.forEach { a ->
            for (i in 0.until(arrayTo!!.size)) {
                val b = arrayTo.get(i)
                if (a != b && Physics.checkCollision(a, b) && !a.isSensor(b) && !b.isSensor(a)) {
                    a.collidesWith(b)
                    Physics.resolveOverlap(a, b)
                }
            }
        }
    }

    companion object {
        val particles = GdxArr<Particle>()
        val thingsToAdd = GdxArr<Thing>()

        fun add(thing: Thing) {
            thingsToAdd.add(thing)
        }
    }

    fun debug(drawer: GdxDrawer) {
        walls().forEach { it.debug(drawer) }
    }

}