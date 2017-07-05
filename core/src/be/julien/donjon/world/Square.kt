package be.julien.donjon.world

import be.julien.donjon.graphics.Drawer

class Square(val line: Int, val row: Int) {

    fun draw(drawer: Drawer) {
        drawer.drawAbsolute(line, row)
    }

}