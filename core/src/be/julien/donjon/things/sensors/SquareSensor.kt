package be.julien.donjon.things.sensors

import be.julien.donjon.GdxArr
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.physics.Mask
import be.julien.donjon.world.shapes.RectShape
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.Energy
import be.julien.donjon.things.Thing
import com.badlogic.gdx.graphics.Color

class SquareSensor internal constructor(var anchor: Thing, sensorLength: Float, val offsetAngle: Float, width: Float): Thing(RectShape.get(width)) {

    internal val colliders = GdxArr<Thing>()
    internal val offset = Vec2.get(sensorLength, 0f)
    internal val haldWidth = width / 2f

    override fun collidesWith(thing: Thing) {
        colliders.add(thing)
    }

    override fun act(delta: Float): Boolean {
        offset.setAngle(offsetAngle + anchor.angle())
        shape.setPos(anchor.shape.x() + offset.x - haldWidth, anchor.shape.y() + offset.y - haldWidth)
        return dead
    }

    override fun draw(drawer: Drawer) {
        drawer.color(Color.RED)
        super.draw(drawer)
    }

    override fun mask(): Mask = Mask.Sensor

    companion object {
        fun get(anchor: Thing, sensorLength: Float, offsetAngle: Float, width: Float): SquareSensor {
            return SquareSensor(anchor, sensorLength, offsetAngle, width)
        }
    }

    fun checked() {
        colliders.clear()
    }

    fun containersEnergy(): Boolean {
        colliders.forEach { if (it is Energy) return true }
        return false
    }
}