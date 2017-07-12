package be.julien.donjon.physics

import be.julien.donjon.life.Life
import be.julien.donjon.util.Util
import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold

class CollisionMaster : ContactListener {

    init {
    }

    override fun preSolve(c: Contact, oldManifold: Manifold): Unit {}
    override fun postSolve(contact: Contact, impulse: ContactImpulse): Unit {}
    override fun endContact(c: Contact): Unit {}

    override fun beginContact(c: Contact): Unit {
        val dataA = c.fixtureA.userData
        val dataB = c.fixtureB.userData

        sortCollision(dataA, dataB, c)
        sortCollision(dataB, dataA, c)
        Util.out("PEROUT")
        println("collision")
    }

    private fun sortCollision(obj: Any, other: Any, contact: Contact): Unit {
        when (obj) {
            is Life -> obj.collidesWith(other)
        }
    }
}