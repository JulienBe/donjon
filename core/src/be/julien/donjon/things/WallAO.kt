package be.julien.donjon.things

import be.julien.donjon.graphics.AssetMan
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.physics.Mask
import be.julien.donjon.physics.Physics
import be.julien.donjon.physics.shapes.Shape
import be.julien.donjon.physics.shapes.SquareAO
import be.julien.donjon.util.spatial.Dimension
import be.julien.donjon.util.spatial.Vec2
import com.badlogic.gdx.graphics.Color

class WallAO(x: Float, y: Float, width: Float, height: Float,
             val dim: Dimension = Dimension.get(width, height)): Thing(Vec2.get(x, y), Vec2.get(0f, 0f)) {

    private val normals = arrayOf(
        Normal(
                x, y,                                    // bottom left
                x + width, y,                   // bottom right
                x + width / 2, y + height / 2, "Down"),
        Normal(
                x + width, y + height,     // top right
                x, y + height,                  // top left
                x + dim.halfWidth, y + dim.halfHeight, "Top"),
       Normal(
                x, y + height,                 // top left
                x, y,                                   // bottom left
               x + dim.halfWidth, y + dim.halfHeight, "LEFT"),
        Normal(
                x + width, y,                    // bottom right
                x + width, y + height,  // top right
                x + dim.halfWidth, y + dim.halfHeight, "RIGHT")
    )

    override fun shape(): Shape = SquareAO
    override fun dimension(): Dimension = dim
    override fun mask(): Mask = Mask.Wall

    fun normal(t: Thing): Float {
        normals.forEach {
            if (it.within(t.centerX(), t.centerY())) {
                return it.normal
            }
        }
        return 0f
    }

    override fun debug(drawer: Drawer) {
        super.debug(drawer)
        normals.forEach {
            drawer.color(Color.YELLOW)
            drawer.draw(AssetMan.square, centerX(), centerY(), 0f, 0f, 20f, 0.2f, it.normal)
        }
    }

    companion object {
        val width = 2f
    }
}

private class Normal(val x1: Float, val y1: Float, val x2: Float, val y2: Float, val centerX: Float, val centerY: Float, val tag: String) {
    val normal: Float = Physics.angle(x1, y1, x2, y2) + 90f
    fun within(x: Float, y: Float): Boolean {
        return Physics.intersectLines(x1, y1, x2, y2, centerX, centerY, x, y)
    }
}