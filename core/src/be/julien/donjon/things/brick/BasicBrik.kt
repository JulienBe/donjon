package be.julien.donjon.things.brick

import be.julien.donjon.graphics.AssetMan
import be.julien.seed.basics.Dimension
import be.julien.seed.basics.Thing
import be.julien.seed.physics.Vec2

class BasicBrik(anchor: Vec2): Thing(anchor, Vec2.zero) {
    override val dimension: Dimension
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val img: Any
        get() = AssetMan.square
}