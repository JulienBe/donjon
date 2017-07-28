package be.julien.donjon.graphics

import be.julien.donjon.things.Thing
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Drawer {
    internal val pixel = Texture(Gdx.files.internal("square.png"))
    internal val batch = SpriteBatch()
    internal val cam = cam()

    private fun cam(): OrthographicCamera {
        val camera = OrthographicCamera(screenWidth, screenHeight)
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0f)
        camera.update()
        return camera
    }

    fun batch(stuffToDraw: (drawer: Drawer) -> Unit) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        cam.update()
        batch.projectionMatrix = cam.combined

        batch.begin()
        stuffToDraw.invoke(this)
        batch.end()
    }

    fun dispose() {
        batch.dispose()
    }

    fun color(color: Color) {
        batch.color = color
    }

    companion object {
        internal val ratio =  Gdx.graphics.width / Gdx.graphics.height
        internal val screenWidth: Float = 160f
        internal val screenHeight: Float = screenWidth / ratio
    }

    fun drawAbsolute(t: Thing) {
        batch.draw(pixel, t.x(), t.y(), t.dimension().width, t.dimension().height)
        batch.color = Color.WHITE
    }
}