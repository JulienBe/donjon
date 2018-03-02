package be.julien.donjon.ui

import com.badlogic.gdx.Screen

abstract class MyScreen : Screen {
    override fun hide() {}
    override fun show() {}
    override fun pause() {}
    override fun resume() {}
    override fun resize(p0: Int, p1: Int) {}
    override fun dispose() {}
}