package be.julien.donjon.physics

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.util.Util
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World

object BoxWorld {
    private val world = World(Vector2.Zero, true)
    private val debugRenderer = Box2DDebugRenderer()
    private val timestep = 1f/45f
    private val velocity = 6
    private val position = 2
    private var accumulator = 0f

    fun init() {}

    fun act(deltaTime: Float) {
        // fixed time step max frame time to avoid spiral of death (on slow devices)
        val frameTime = Math.min(deltaTime, 0.25f)
        accumulator += frameTime
        while (accumulator >= timestep) {
            world.step(timestep, velocity, position)
            accumulator -= timestep
        }
    }

    fun render(dra: Drawer) {
        debugRenderer.render(world, dra.cam.combined)
    }

    fun createBody(bodyDef: BodyDef): Body {
        return world.createBody(bodyDef)
    }
}