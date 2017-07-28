package be.julien.donjon.physics.shapes

import be.julien.donjon.physics.Physics
import be.julien.donjon.things.Thing

object Square : Shape {
    override fun collidesWith(me: Thing, other: Thing): Boolean {
        val otherShape = other.shape()
        when (otherShape) {
            Square -> return Physics.squareSquare(me, other)
        }
        return false
    }
}