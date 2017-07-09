package be.julien.donjon.world

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.spatial.Pos

class Square(val pos: Pos) {

    fun draw(drawer: Drawer) {
        drawer.drawAbsolute(pos)
    }

}