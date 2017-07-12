package be.julien.donjon.physics

import be.julien.donjon.spatial.Vec2
import be.julien.donjon.spatial.Vec2Comp
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*

object BoxHelper {

    internal val ppm = 20f

    fun createRectangle(bodyType: BodyDef.BodyType, rectangle: Rectangle, boxObject: BoxObject, pos: Vec2): BoxBody {
        val b = BoxBody(createBody(bodyType, createRectangleShape(rectangle), boxObject, pos))
        return b
    }

    private fun createBody(bodyType: BodyDef.BodyType, shape: Shape, boxObject: BoxObject, pos: Vec2): Body {
        val b = BoxWorld.createBody(createBodyDef(bodyType))
        createFixture(b, shape, boxObject)
        shape.dispose()
        b.isFixedRotation = true
        b.angularDamping = 0f
        b.angularVelocity = 0f
        b.gravityScale = 0f
        b.linearDamping = 0f
        b.linearVelocity = Vector2.Y
        b.setTransform(pos, 0f)
        return b
    }

    private fun createBodyDef(bodyType: BodyDef.BodyType): BodyDef  {
        val bodyDef = BodyDef()
        bodyDef.`type` = bodyType
        bodyDef.allowSleep = false
        return bodyDef
    }

    private fun createFixture(b: Body, shape: Shape, obj: BoxObject): Fixture {
        val fixtureDef = FixtureDef()
        fixtureDef.shape = shape
//        fixtureDef.filter.categoryBits = category
//        fixtureDef.filter.maskBits = mask
        fixtureDef.isSensor = false
        fixtureDef.density = 0f
        fixtureDef.friction = 0f
        fixtureDef.restitution = 0f
        val fixture = b.createFixture(fixtureDef)
        fixture.userData = obj
        return fixture
    }

    private fun createRectangleShape(rectangle: Rectangle): Shape {
        val polygon = PolygonShape()
        val center = Vec2Comp.get(rectangle.x + rectangle.width * 0.5f, rectangle.y + rectangle.height * 0.5f)
        polygon.setAsBox((rectangle.width * 0.5f), (rectangle.height * 0.5f), center, 0.0f)
        return polygon
    }

}