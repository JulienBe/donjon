package be.julien.donjon.physics.b2d

import be.julien.donjon.spatial.Vec2
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body

class BoxBody(val body: Body, internal var speed: Float) {

    fun steer(angle: Float) {
        Vec2.global.set(body.linearVelocity)
        Vec2.global.rotate(angle)
        applyForce(Vec2.global)
    }

    private fun applyForce(v2: Vector2) {
        v2.nor().scl(speed)
        body.applyForceToCenter(v2, true)
        val velocity = body.linearVelocity
        if (velocity.len() > speed) {
            velocity.nor().scl(speed)
            body.linearVelocity = velocity
        }
    }

    fun pos(): Vector2 {
        return body.position
    }
}