package be.julien.donjon.graphics

import be.julien.donjon.spatial.Vec2
import be.julien.donjon.spatial.Pos
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
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
        val camera = OrthographicCamera(30f * ratio, 30f)
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

    fun drawAbsolute(pos: Pos) {
        batch.draw(pixel, pos.x.toFloat(), pos.y.toFloat())
        batch.setColor(Color.WHITE)
    }
    fun drawAbsolute(pos: Vec2) {
        batch.draw(pixel, pos.x, pos.y)
        batch.setColor(Color.WHITE)
    }

    fun color(color: Color) {
        batch.setColor(color)
    }
}