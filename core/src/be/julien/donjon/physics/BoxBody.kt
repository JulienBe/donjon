package be.julien.donjon.physics

import be.julien.donjon.spatial.Vec2
import be.julien.donjon.spatial.Vec2Comp
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body

class BoxBody(val body: Body) {

    fun steer(angle: Float, delta: Float) {
        Vec2Comp.global.set(body.linearVelocity)
        Vec2Comp.global.rotate(angle * delta)
        body.linearVelocity = Vec2Comp.global
    }

    fun initSpeed(speed: Float) {
        Vec2Comp.global.set(body.linearVelocity)
        Vec2Comp.global.nor().scl(speed)
        body.linearVelocity = Vec2Comp.global
    }

    fun pos(): Vector2 {
        return body.position
    }
}