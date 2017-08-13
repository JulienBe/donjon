package be.julien.donjon.graphics

import com.badlogic.gdx.graphics.g2d.TextureRegion

interface Drawable {
    fun hw(): Float
    fun w(): Float
    fun hh(): Float
    fun h(): Float
    fun angle(): Float
    fun x(): Float
    fun y(): Float
    fun tr(): TextureRegion
    fun debug(drawer: Drawer) {
    }
}