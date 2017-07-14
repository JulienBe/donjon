package be.julien.donjon.physics.b2d

import be.julien.donjon.spatial.Vec2
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*

object BoxHelper {

    internal val ppm = 20f

    fun createRectangle(bodyType: BodyDef.BodyType, rectangle: Rectangle): BoxBody {
        val b = BoxBody(createBody(bodyType, createRectangleShape(rectangle)))
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

    private fun createRectangleShape(rectangle: Rectangle): Shape {
        val polygon = PolygonShape()
        val center = Vec2.get(rectangle.x + rectangle.width * 0.5f, rectangle.y + rectangle.height * 0.5f)
        polygon.setAsBox((rectangle.width * 0.5f), (rectangle.height * 0.5f), center, 0.0f)
        return polygon
    }

}