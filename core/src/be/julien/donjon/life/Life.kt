package be.julien.donjon.life

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.util.Util
import com.badlogic.gdx.graphics.Color

abstract class Life(val pos: Vec2) {
    abstract fun act(delta: Float)
    fun draw(drawer: Drawer) {
        drawer.color(Color.BLACK)
        drawer.drawAbsolute(pos)
        Util.out(pos.toString())
    }
}