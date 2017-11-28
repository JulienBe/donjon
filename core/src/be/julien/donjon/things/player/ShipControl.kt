package be.julien.donjon.things.player

import be.julien.donjon.GdxDrawer
import be.julien.seed.utils.InputHub
import com.badlogic.gdx.Input

class ShipControl(val pawn: Ship, inputHub: InputHub) {

    init {
        inputHub.addKeyPressed(Input.Keys.Z, { pawn.up() } )
        inputHub.addKeyPressed(Input.Keys.Q, { pawn.left() } )
        inputHub.addKeyPressed(Input.Keys.S, { pawn.down() } )
        inputHub.addKeyPressed(Input.Keys.D, { pawn.right() } )
        inputHub.addKeyUp(Input.Keys.Z, { pawn.notVertical() } )
        inputHub.addKeyUp(Input.Keys.Q, { pawn.notLateral() } )
        inputHub.addKeyUp(Input.Keys.S, { pawn.notVertical() } )
        inputHub.addKeyUp(Input.Keys.D, { pawn.notLateral() } )
        inputHub.addClick({ pawn.click(GdxDrawer.xClick(), GdxDrawer.yClick()) })
    }

}