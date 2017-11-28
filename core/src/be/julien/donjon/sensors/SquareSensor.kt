package be.julien.donjon.sensors

import be.julien.donjon.graphics.AssetMan
import be.julien.seed.physics.shapes.Shape
import be.julien.seed.physics.shapes.SquareAO
import be.julien.seed.basics.Dimension
import be.julien.seed.basics.Thing
import be.julien.seed.graphics.Color
import be.julien.seed.graphics.Drawer
import be.julien.seed.physics.Vec2
import be.julien.seed.sensors.Sensor

class SquareSensor internal constructor(anchor: Thing, sensorLength: Float, val offsetAngle: Float, width: Float):
        Sensor(anchor, img) {

    internal val offset = Vec2.get(sensorLength, 0f)
    internal val dim = Dimension.get(width, width)

    override fun shape(): Shape = SquareAO
    override fun dimension(): Dimension = dim

    override fun act(delta: Float): Boolean {
        offset.setAngle(offsetAngle + anchor.angle())
        pos.set(anchor.x() + anchor.hw() + offset.x() - hw(), anchor.y() + anchor.hh() + offset.y() - hh())
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