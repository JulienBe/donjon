package be.julien.donjon.physics.b2d

import be.julien.donjon.things.life.Life
import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold

class CollisionMaster : ContactListener {

    override fun preSolve(c: Contact, oldManifold: Manifold): Unit {}
    override fun postSolve(contact: Contact, impulse: ContactImpulse): Unit {}
    override fun endContact(c: Contact): Unit {}

    override fun beginContact(c: Contact): Unit {
        val dataA = c.fixtureB.body.userData
        val dataB = c.fixtureB.body.userData

        println("" + dataA + " with " + dataB)
        if (dataA != null && dataB != null)
            sortCollision(dataA, dataB, c)
    }

    private fun sortCollision(a: Any, b: Any, contact: Contact): Unit {
        if (a is Life && b is Life) {
            a.collidesWith(b)
            b.collidesWith(a)
        }
    }
}