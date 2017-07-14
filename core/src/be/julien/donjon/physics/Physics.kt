package be.julien.donjon.physics

import be.julien.donjon.life.Life

object Physics {

    fun intersect(l1: Life, l2: Life): Boolean {
        return l1.rect.overlaps(l2.rect)
    }
}