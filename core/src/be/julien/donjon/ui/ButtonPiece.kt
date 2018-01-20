package be.julien.donjon.ui

import be.julien.donjon.graphics.AssetMan
import be.julien.seed.physics.Vec2
import be.julien.seed.ui.FontPiece

class ButtonPiece(col: Int, row: Int, parent: Vec2) : FontPiece(col, row, parent) {
    override val img: Any
        get() = AssetMan.square

    companion object {
        fun getPiece(col: Int, row: Int, parent: Vec2): FontPiece {
            return ButtonPiece(col, row, parent)
        }
    }
}