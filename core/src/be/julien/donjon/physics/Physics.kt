package be.julien.donjon.physics

import be.julien.donjon.things.Thing

object Physics {
    fun squareSquare(me: Thing, other: Thing): Boolean {
        return me.x() < other.x() + other.w() &&
                me.x() + me.w() > other.x() &&
                me.y() < other.y() + other.h() &&
                me.h() + me.y() > other.y()
    }
}
    
