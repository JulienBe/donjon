package be.julien.donjon.physics

import be.julien.donjon.GdxArr
import be.julien.donjon.things.Thing

object Collider {
    fun check(things: GdxArr<Thing>) {
        for (i in 0.until(things.size)) {
            val a = things[i]
            for (j in (i + 1).until(things.size)) {
                val b = things[j]
                if (Physics.intersect(a, b)) {
                    things[i].collidesWith(a)
                    things[j].collidesWith(b)
                }
            }
        }
    }
}