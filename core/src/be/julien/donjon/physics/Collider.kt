package be.julien.donjon.physics

import be.julien.donjon.GdxArr
import be.julien.donjon.life.Life

object Collider {
    fun check(lifeforms: GdxArr<Life>) {
        for (i in 0.until(lifeforms.size))
            for (j in (i+1).until(lifeforms.size))
                if (Physics.intersect(lifeforms[i], lifeforms[j])) {
                    lifeforms[i].collidesWith(lifeforms[j])
                    lifeforms[j].collidesWith(lifeforms[i])
                }
    }
}