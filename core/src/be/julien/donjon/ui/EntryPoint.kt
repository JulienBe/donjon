package be.julien.donjon.ui

import com.badlogic.gdx.Game

class EntryPoint : Game() {
    override fun create() {
        setScreen(MainMenu(this))
    }
}