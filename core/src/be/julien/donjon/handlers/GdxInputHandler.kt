package be.julien.donjon.handlers

import be.julien.seed.utils.InputHandler
import com.badlogic.gdx.Gdx

object GdxInputHandler : InputHandler {
    override fun isKeyJustPressed(keycode: Int): Boolean = Gdx.input.isKeyJustPressed(keycode)
    override fun isKeyPressed(keycode: Int): Boolean = Gdx.input.isKeyPressed(keycode)
    override fun isTouched(): Boolean = Gdx.input.isTouched
}