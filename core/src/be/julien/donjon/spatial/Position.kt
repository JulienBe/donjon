package be.julien.donjon.spatial

import be.julien.donjon.world.WorldWiz

class Position(var x: Int, var y: Int) {
    fun move(dir: Direction) {
        x += dir.x
        y += dir.y
        x = WorldWiz.trimX(x)
        y = WorldWiz.trimY(y)
    }
}

object PositionPool {
    fun get(x: Int, y: Int): Position {
        return Position(x, y)
    }
}