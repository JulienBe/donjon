package be.julien.donjon.things.sensors

import be.julien.donjon.graphics.AssetMan
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.physics.shapes.Circle
import be.julien.donjon.physics.shapes.Shape
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.things.Thing
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion

class RoundSensor private constructor(anchor: Thing, radius: Float): Sensor(anchor) {

    val dim = Dimension.get(radius, radius)
    override fun shape(): Shape = Circle
    override fun dimension(): Dimension = dim

    override fun act(delta: Float): Boolean {
        pos.x = anchor.centerX() - dim.halfWidth
        pos.y = anchor.centerY() - dim.halfHeight
        return dead
    }

    override fun draw(drawer: Drawer) {
//        drawer.color(Color.GRAY)
//        super.draw(drawer)
    }

    override fun tr(): TextureRegion {
        return AssetMan.circle
    }

    companion object {
        fun get(anchor: Thing, radius: Float): RoundSensor {
            return RoundSensor(anchor, radius)
        }
    }

}