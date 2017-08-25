package be.julien.donjon.graphics

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion

class Drawer {
    internal val batch = SpriteBatch()
    internal val cam = cam()
    private val asset = AssetMan()

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

    fun drawAO(d: Drawable) {
        batch.draw(d.tr(), d.x(), d.y(), d.w(), d.h())
        color(Color.WHITE)
    }
    fun drawNAO(d: Drawable) {
        batch.draw(d.tr(), d.x(), d.y(), d.hw(), d.hh(), d.w(), d.h(), 1f, 1f, d.angle())
        color(Color.WHITE)
    }

    fun drawAO(textureRegion: TextureRegion, x: Float, y: Float, width: Float, height: Float) {
        batch.draw(textureRegion, x, y, width, height)
    }

    fun draw(square: TextureRegion, x: Float, y: Float, pivotX: Float, pivotY: Float, width: Float, height: Float, angle: Float) {
        batch.draw(square, x, y, pivotX, pivotY, width, height, 1f, 1f, angle)
        color(Color.WHITE)
    }

    companion object {
        private val ratio: Float =  Gdx.graphics.width.toFloat() / Gdx.graphics.height.toFloat()
        internal val screenWidth: Float = 160f
        internal val screenHeight: Float = screenWidth / ratio
        private val widthRatio: Float =  screenWidth / Gdx.graphics.width
        private val heightRatio: Float =  screenHeight / Gdx.graphics.height

        fun xClick(): Float = Gdx.input.getX(0) * widthRatio
        fun yClick(): Float = screenHeight - (Gdx.input.getY(0) * heightRatio)
    }

}