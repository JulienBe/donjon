package be.julien.donjon.physics

import be.julien.donjon.GdxArr
import be.julien.donjon.things.Thing

object Collider {
    fun check(things: GdxArr<Thing>) {
        for (i in 0.until(things.size))
            for (j in (i+1).until(things.size))
                if (Physics.intersect(things[i], things[j])) {
                    things[i].collidesWith(things[j])
                    things[j].collidesWith(things[i])
                }
    }
}