package be.julien.donjon.ui

import be.julien.donjon.GdxDrawer
import be.julien.donjon.Globals
import be.julien.seed.physics.Vec2
import be.julien.seed.time.Time
import be.julien.seed.ui.Button
import be.julien.seed.ui.Menu
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx

class MainMenu(val game: Game) : MyScreen() {

    val play = Button(
            Vec2.get(72f, 50f),
            arrayOf("**",
                    "* **",
                    "*   **",
                    "*     **",
                    "*   **",
                    "* **",
                    "**"),
            ButtonPiece.Companion::getPiece,
            this::changeToGameScreen
            )
    val menu = Menu(arrayOf(
            play
    ))

    private fun changeToGameScreen() {
        println("MainMenu.changeToGameScreen")
        game.screen = Loop()
    }

    override fun render(delta: Float) {
        Time.act(Gdx.graphics.deltaTime)
        menu.act(GdxUiInput)
        Globals.drawer.batch(this::draw)
    }

    fun draw(drawer: GdxDrawer) {
        menu.draw(drawer)
    }
}