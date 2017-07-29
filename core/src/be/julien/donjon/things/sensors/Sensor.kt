package be.julien.donjon.things.sensors

import be.julien.donjon.GdxArr
import be.julien.donjon.physics.Mask
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.Energy
import be.julien.donjon.things.Thing

abstract class Sensor: Thing(Vec2.get(0f, 0f), Vec2.get(0f, 0f)) {
    internal val colliders = GdxArr<Thing>()

    override fun mask(): Mask = Mask.Sensor

    fun checked() {
        colliders.clear()
    }

    fun containersEnergy(): Boolean {
        colliders.forEach { if (it is Energy) return true }
        return false
    }

    override fun collidesWith(thing: Thing) {
        colliders.add(thing)
    }
}