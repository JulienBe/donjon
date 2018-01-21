package be.julien.donjon.ui

import be.julien.donjon.GdxDrawer
import be.julien.seed.physics.Vec2
import be.julien.seed.ui.UiInput
import com.badlogic.gdx.Gdx

object GdxUiInput: UiInput {
    override fun justClicked(): Boolean {
        return Gdx.input.justTouched()
    }

    override fun mousePos(): Vec2 {
        return Vec2.tmp.set(GdxDrawer.xClick(), GdxDrawer.yClick())
    }
}