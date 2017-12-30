package be.julien.donjon.things

import be.julien.donjon.graphics.AssetMan
import be.julien.seed.basics.WallAO
import be.julien.seed.basics.WallSide

class WallAOBasic(x: Float, y: Float, width: Float, height: Float, side: WallSide): WallAO(x, y, width, height, side) {
    override val img: Any
        get() = AssetMan.square
}