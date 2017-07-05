package be.julien.donjon.spatial

class Dimension(internal val row: Int, internal val col: Int) {
    internal val surface = row * col
}