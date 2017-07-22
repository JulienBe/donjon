package be.julien.donjon.physics

import be.julien.donjon.things.Thing

object Physics {

    fun intersect(t1: Thing, t2: Thing): Boolean {
        return t1.shape.overlaps(t2.shape)
    }
}