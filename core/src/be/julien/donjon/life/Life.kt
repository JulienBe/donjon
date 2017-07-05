package be.julien.donjon.life

import be.julien.donjon.spatial.Position

abstract class Life(val pos: Position) {
    abstract fun act()
}