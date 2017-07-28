package be.julien.donjon.physics.shapes

import be.julien.donjon.things.Thing

object Circle : Shape {
    override fun collidesWith(me: Thing, other: Thing): Boolean {
        val otherShape = other.shape()
        return true
    }

}