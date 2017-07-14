package be.julien.donjon.graphics

import be.julien.donjon.physics.b2d.BoxHelper
import be.julien.donjon.spatial.Rect
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

class Drawer {
    internal val pixel = Texture(Gdx.files.internal("square.png"))
    internal val batch = SpriteBatch()
    internal val screenWidth: Float = Gdx.graphics.width / BoxHelper.ppm
    internal val screenHeight: Float = Gdx.graphics.height / BoxHelper.ppm
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
        batch.setProjectionMatrix(cam.combined)

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

    fun drawAbsolute(pos: Vector2, size: Rectangle) {
        batch.draw(pixel, pos.x, pos.y, size.width, size.height)
        batch.color = Color.WHITE
    }
    fun drawAbsolute(rect: Rect) {
        batch.draw(pixel, rect.x, rect.y, rect.width, rect.height)
        batch.color = Color.WHITE
    }
}