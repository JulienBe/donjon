package be.julien.donjon.things.sensors

import be.julien.donjon.GdxArr
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.physics.Mask
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.Energy
import be.julien.donjon.things.Thing
import com.badlogic.gdx.graphics.Color

class SquareSensor internal constructor(var anchor: Thing, sensorLength: Float, val offsetAngle: Float, width: Float):
        Thing(Vec2.get(0f, 0f), Vec2.get(0f, 0f)) {

    internal val colliders = GdxArr<Thing>()
    internal val offset = Vec2.get(sensorLength, 0f)
    internal val dim = Dimension.get(width, width)

    override fun dimension(): Dimension = dim

    override fun collidesWith(thing: Thing) {
        colliders.add(thing)
    }

    override fun act(delta: Float): Boolean {
        offset.setAngle(offsetAngle + anchor.angle())
        pos.set(anchor.x() + anchor.hw() + offset.x - hw(), anchor.y() + anchor.hh() + offset.y - hh())
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