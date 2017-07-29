package be.julien.donjon.world

import be.julien.donjon.GdxArr
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.particles.Particle
import be.julien.donjon.physics.Mask
import be.julien.donjon.physics.Physics
import be.julien.donjon.things.Thing

class Collect {
    internal val stuff = HashMap<Mask, GdxArr<Thing>>()
    internal val removedThings = GdxArr<Thing>()
    internal val particles = GdxArr<Particle>()
    internal var keys: MutableSet<Mask>

    init {
        stuff.put(Mask.Life, GdxArr<Thing>())
        stuff.put(Mask.Sensor, GdxArr<Thing>())
        stuff.put(Mask.Wall, GdxArr<Thing>())
        stuff.put(Mask.Energy, GdxArr<Thing>())
        keys = stuff.keys
    }

    fun add(vararg things: Thing) {
        things.forEach {
            stuff[it.mask()]!!.add(it)
            stuff[Mask.Sensor]!!.addAll(it.sensors)
        }
    }

    fun remove(vararg things: Thing) {
        things.forEach {
            stuff[it.mask()]!!.removeValue(it, true)
        }
    }

    fun act(delta: Float) {
        particles.addAll(particlesToAdd)
        particles.removeAll(particlesToRemove, true)
        particlesToAdd.clear()
        particles.forEach {
            if (it.act())
                particlesToRemove.add(it)
        }

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

    fun draw(drawer: Drawer) {
        stuff.forEach { mask, gdxArr ->
            gdxArr.forEach { it.draw(drawer) }
        }
        particles.forEach { it.draw(drawer) }
    }

    fun nbEnergy(): Int = stuff[Mask.Energy]!!.size

    fun check() {
        for (i in 0.until(keys.size)) {
            checkMask(i)
        }
    }

    private fun checkMask(i: Int) {
        val maskA = keys.elementAt(i)
        for (j in i.until(keys.size)) {
            val maskB = keys.elementAt(j)
            if (maskA.collidesWith(maskB)) {
                checkCollision(maskA, maskB)
            } else if (maskB.collidesWith(maskA)) {
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
                if (Physics.checkCollision(a, b) && a != b && !a.isSensor(b) && !b.isSensor(a )) {
                    a.collidesWith(b)
                    b.collidesWith(a)
                }
            }
        }
    }

    companion object {
        val particlesToAdd = GdxArr<Particle>()
        val particlesToRemove = GdxArr<Particle>()

        fun add(particle: Particle) {
            particlesToAdd.add(particle)
        }
    }
}