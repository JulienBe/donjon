package be.julien.donjon.world

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.spatial.Position

class Square(val pos: Position) {

    fun draw(drawer: Drawer) {
        drawer.drawAbsolute(pos)
    }

}