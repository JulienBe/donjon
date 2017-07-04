package be.julien.donjon.world

import be.julien.donjon.Drawer

/**
 * Created by julien on 04/07/17.
 */
class Square(val line: Int, val row: Int) {

    fun draw(drawer: Drawer) {
        drawer.drawAbsolute(line, row)
    }

}