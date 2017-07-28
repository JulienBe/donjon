package be.julien.donjon.physics.shapes

import be.julien.donjon.things.Thing

object Square : Shape {
    override fun collidesWith(me: Thing, other: Thing): Boolean {
        return true
    }
}