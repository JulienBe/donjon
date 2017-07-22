package be.julien.donjon.physics.b2d

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*

object BoxHelper {

    fun createRectangle(bodyType: BodyDef.BodyType, shape: be.julien.donjon.world.shapes.Shape): BoxBody {
        val b = BoxBody(createBody(bodyType, shape.b2dShape()))
        return b
    }

    private fun createBody(bodyType: BodyDef.BodyType, shape: Shape): Body {
        val b = BoxWorld.createBody(createBodyDef(bodyType))
        createFixture(b, shape)
        shape.dispose()
        b.isFixedRotation = true
        b.angularDamping = 0f
        b.angularVelocity = 0f
        b.gravityScale = 0f
        b.linearDamping = 0f
        b.linearVelocity = Vector2.Y
        b.setTransform(Vector2.Zero, 0f)
        return b
    }

    private fun createBodyDef(bodyType: BodyDef.BodyType): BodyDef  {
        val bodyDef = BodyDef()
        bodyDef.`type` = bodyType
        bodyDef.allowSleep = false
        return bodyDef
    }

    private fun createFixture(b: Body, shape: Shape): Fixture {
        val fixtureDef = FixtureDef()
        fixtureDef.shape = shape
//        fixtureDef.filter.categoryBits = category
//        fixtureDef.filter.maskBits = mask
        fixtureDef.isSensor = false
        fixtureDef.density = 0f
        fixtureDef.friction = 0f
        fixtureDef.restitution = 0f
        val fixture = b.createFixture(fixtureDef)
        return fixture
    }

}