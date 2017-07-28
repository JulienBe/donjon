package be.julien.donjon.physics.shapes

import be.julien.donjon.things.Thing

interface Shape {
    fun collidesWith(me: Thing, other: Thing): Boolean
}