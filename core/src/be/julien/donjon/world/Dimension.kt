package be.julien.donjon.world

/**
 * Created by julien on 04/07/17.
 */
class Dimension(internal val row: Int, internal val col: Int) {
    internal val surface = row * col
}