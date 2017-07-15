package be.julien.donjon.things

import be.julien.donjon.GdxArr
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.spatial.Rect
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.util.Util
import com.badlogic.gdx.graphics.Color

class Sensor internal constructor(var anchor: Thing, sensorLength: Float, val offsetAngle: Float, width: Float): Thing(Rect.get(width)) {

    internal val colliders = GdxArr<Thing>()
    internal val offset = Vec2.get(sensorLength, 0f)
    internal val haldWidth = width / 2f

    override fun collidesWith(thing: Thing) {
        colliders.add(thing)
    }

    override fun act(delta: Float): Boolean {
        colliders.clear()
        offset.setAngle(offsetAngle + anchor.angle())
        rect.set(anchor.rect.centerX() + offset.x - haldWidth, anchor.rect.centerY() + offset.y - haldWidth)
        return false
    }

    override fun draw(drawer: Drawer) {
        Util.out("DRAW")
        drawer.color(Color.RED)
        super.draw(drawer)
    }

    companion object {
        fun get(anchor: Thing, sensorLength: Float, offsetAngle: Float, width: Float): Sensor {
            return Sensor(anchor, sensorLength, offsetAngle, width)
        }
    }
}