package be.julien.donjon.physics.b2d

import be.julien.donjon.spatial.Vec2
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*

class BoxBody(val body: Body) {

    fun x(): Float = body.position.x
    fun y(): Float = body.position.y

    fun setPos(x: Float, y: Float) {
        body.setTransform(x, y, 0f)
    }

    fun mv(dir: Vec2, delta: Float) {
        body.setTransform(body.position.x + dir.x * delta, body.position.y + dir.y * delta, body.angle)
    }

    companion object {
        fun getRect(width: Float, height: Float): BoxBody {
            val polygon = PolygonShape()
            val center = Vec2.get(width * 0.5f, height * 0.5f)
            polygon.setAsBox(width * 0.5f, height * 0.5f, center, 0.0f)
            return BoxHelper.createRectangle(polygon)
        }
        fun getRect(x: Float, y: Float, width: Float, height: Float): BoxBody {
            val body = getRect(width, height)
            body.setPos(x, y)
            return body
        }
    }

}

internal object BoxHelper {
    fun createRectangle(shape: com.badlogic.gdx.physics.box2d.Shape): BoxBody {
        val b = BoxBody(createBody(shape))
        return b
    }

    private fun createBody(shape: com.badlogic.gdx.physics.box2d.Shape): Body {
        val b = BoxWorld.createBody(createBodyDef())
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

    private fun createBodyDef(): BodyDef  {
        val bodyDef = BodyDef()
        bodyDef.`type` = BodyDef.BodyType.DynamicBody
        bodyDef.allowSleep = false
        return bodyDef
    }

    private fun createFixture(b: Body, shape: com.badlogic.gdx.physics.box2d.Shape): Fixture {
        val fixtureDef = FixtureDef()
        fixtureDef.shape = shape
//        fixtureDef.filter.categoryBits = category
//        fixtureDef.filter.maskBits = mask
        fixtureDef.isSensor = true
        fixtureDef.density = 0f
        fixtureDef.friction = 0f
        fixtureDef.restitution = 0f
        val fixture = b.createFixture(fixtureDef)
        return fixture
    }
}