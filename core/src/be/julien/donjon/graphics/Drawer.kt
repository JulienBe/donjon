package be.julien.donjon.graphics

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion

class Drawer {
    internal val pixel = TextureRegion(Texture(Gdx.files.internal("square.png")))
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
        internal val ratio: Float =  Gdx.graphics.width.toFloat() / Gdx.graphics.height.toFloat()
        internal val screenWidth: Float = 160f
        internal val screenHeight: Float = screenWidth / ratio
    }

    fun drawAO(d: Drawable) {
        batch.draw(pixel, d.x(), d.y(), d.w(), d.h())
        batch.color = Color.WHITE
    }
    fun drawNAO(d: Drawable) {
        batch.draw(pixel, d.x(), d.y(), d.hw(), d.hh(), d.w(), d.h(), 1f, 1f, d.angle())
        batch.color = Color.WHITE
    }

    fun drawAO(textureRegion: TextureRegion, x: Float, y: Float, width: Float, height: Float) {
        batch.draw(textureRegion, x, y, width, height)
    }
}