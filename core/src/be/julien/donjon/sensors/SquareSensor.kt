package be.julien.donjon.sensors

import be.julien.donjon.graphics.AssetMan
import be.julien.seed.physics.shapes.Shape
import be.julien.seed.physics.shapes.SquareAO
import be.julien.seed.basics.Dimension
import be.julien.seed.basics.Sensor
import be.julien.seed.basics.Thing
import be.julien.seed.graphics.Color
import be.julien.seed.graphics.Drawer
import be.julien.seed.physics.Vec2

class SquareSensor internal constructor(anchor: Thing, sensorLength: Float, val offsetAngle: Float, width: Float):
        Sensor(anchor) {

    internal val offset = Vec2.get(sensorLength, 0f)
    internal val dim = Dimension.get(width, width)

    override val img: Any
        get() = AssetMan.square
    override val shape: Shape
        get() = SquareAO
    override val dimension: Dimension
        get() = dim

    override fun act(delta: Float): Boolean {
        offset.setAngle(offsetAngle + anchor.angle)
        pos.set(anchor.x + anchor.hw + offset.x - hw, anchor.y + anchor.hh + offset.y - hh)
        return dead
    }

    override fun draw(drawer: Drawer) {
        drawer.color(Color.WHITE)
        super.draw(drawer)
    }

    companion object {
        val img = AssetMan.square
        fun get(anchor: Thing, sensorLength: Float, offsetAngle: Float, width: Float): SquareSensor {
            return SquareSensor(anchor, sensorLength, offsetAngle, width)
        }
    }

}