package be.julien.donjon

import be.julien.donjon.world.World
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class DonjonMain : ApplicationAdapter() {
    lateinit internal var batch: SpriteBatch
    internal val world = World()

    override fun create() {
        batch = SpriteBatch()
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        world.act()
        world.draw()
        batch.end()
    }

    override fun dispose() {
        batch.dispose()
    }
}
