package be.julien.donjon.util.spatial

class Dimension private constructor(val width: Float, val height: Float) {

    val halfWidth = width / 2f
    val halfHeight = height / 2f

    companion object {
        fun get(width: Float, height: Float): Dimension {
            return Dimension(width, height)
        }
    }
}