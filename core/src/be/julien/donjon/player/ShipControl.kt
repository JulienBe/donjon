package be.julien.donjon.player

import be.julien.donjon.hubs.InputHub
import com.badlogic.gdx.Input

class ShipControl(val pawn: Ship, inputHub: InputHub) {

    init {
        inputHub.addKeyPressed(Input.Keys.Z, { pawn.up() } )
        inputHub.addKeyPressed(Input.Keys.Q, { pawn.left() } )
        inputHub.addKeyPressed(Input.Keys.S, { pawn.down() } )
        inputHub.addKeyPressed(Input.Keys.D, { pawn.right() } )
    }

}