package be.julien.donjon.graphics

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Drawer {
    internal val pixel = Texture(Gdx.files.internal("square.png"))
    internal val batch = SpriteBatch()
    internal val screenWidth = Gdx.graphics.width
    internal val screenHeight = Gdx.graphics.height
    internal val ratio =  screenWidth.toFloat() / screenHeight.toFloat()
    internal val cam = cam()

    private fun cam(): OrthographicCamera {
        val camera = OrthographicCamera(30f * ratio, 30f * ratio)
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0f)
        camera.update()
        return camera
    }

    fun batch(stuffToDraw: (drawer: Drawer) -> Unit) {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        cam.update()
        batch.setProjectionMatrix(cam.combined)

        batch.begin()
        stuffToDraw.invoke(this)
        batch.end()
    }

    fun dispose() {
        batch.dispose()
    }

    fun drawAbsolute(line: Int, row: Int) {
        batch.setColor(0.25f * row.toFloat(), 0.25f * line.toFloat(), 0.33f * line.toFloat(), 1f)
        batch.draw(pixel, line.toFloat(), row.toFloat())
    }
}