package be.julien.donjon.things.player

import be.julien.donjon.graphics.AssetMan
import be.julien.donjon.physics.Mask
import be.julien.donjon.physics.shapes.Circle
import be.julien.donjon.physics.shapes.Shape
import be.julien.donjon.things.Thing
import be.julien.donjon.util.spatial.Dimension
import be.julien.donjon.util.spatial.Vec2
import be.julien.donjon.util.time.Time
import com.badlogic.gdx.graphics.g2d.TextureRegion

class Ship(pos: Vec2): Thing(pos, Vec2.get(0f, 0f)) {
    override fun mask(): Mask = Mask.Life
    override fun shape(): Shape = Circle
    override fun dimension(): Dimension = dim
    override fun angle(): Float = 0f
    override fun x(): Float = pos.x()
    override fun y(): Float = pos.y()
    override fun tr(): TextureRegion = AssetMan.circle
    override fun fast(): Boolean = false

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