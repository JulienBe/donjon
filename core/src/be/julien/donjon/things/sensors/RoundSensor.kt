package be.julien.donjon.things.sensors

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.physics.shapes.Circle
import be.julien.donjon.physics.shapes.Shape
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.things.Thing
import com.badlogic.gdx.graphics.Color

class RoundSensor private constructor(var anchor: Thing, radius: Float): Sensor() {

    val dim = Dimension.get(radius, radius)
    override fun shape(): Shape = Circle
    override fun dimension(): Dimension = dim

    override fun act(delta: Float): Boolean {
        pos.x = anchor.x()
        pos.y = anchor.y()
        return dead
    }

    override fun draw(drawer: Drawer) {
        drawer.color(Color.RED)
        super.draw(drawer)
    }

    companion object {
        fun get(anchor: Thing, radius: Float): RoundSensor {
            return RoundSensor(anchor, radius)
        }
    }

}