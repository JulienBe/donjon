package be.julien.donjon.life

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.spatial.Position
import be.julien.donjon.util.Util
import com.badlogic.gdx.graphics.Color

abstract class Life(val pos: Position) {
    abstract fun act(delta: Float)
    fun draw(drawer: Drawer) {
        drawer.color(Color.BLACK)
        drawer.drawAbsolute(pos)
        Util.out(pos.toString())
    }
}