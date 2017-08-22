package be.julien.donjon.player

import be.julien.donjon.graphics.AssetMan
import be.julien.donjon.graphics.DrawableDim
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.util.Time
import com.badlogic.gdx.graphics.g2d.TextureRegion

class Ship(val pos: Vec2): DrawableDim {

    override fun dimension(): Dimension {
        return dim
    }

    override fun angle(): Float {
        return 0f
    }

    override fun x(): Float {
        return pos.x()
    }

    override fun y(): Float {
        return pos.y()
    }

    override fun tr(): TextureRegion {
        return AssetMan.circle
    }

    fun left() {
        move(-speed * Time.playerDelta, 0f)
    }
    fun right() {
        move(speed * Time.playerDelta, 0f)
    }
    fun up() {
        move(0f, speed * Time.playerDelta)
    }
    fun down() {
        move(0f, -speed * Time.playerDelta)
    }
    fun move(x: Float, y: Float) {
        pos.move(x, y)
    }

    companion object {
        val dim = Dimension.get(3f, 3f)
        val speed = 100f
    }
}