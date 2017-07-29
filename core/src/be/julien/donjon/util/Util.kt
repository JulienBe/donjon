package be.julien.donjon.util

import com.badlogic.gdx.Gdx

object Util {

    fun out(string: String) {
        Gdx.app.log("Out", string)
    }

    fun lineToRow(line: Int, columns: Int): Int {
        return line / columns
    }

    fun lineToCol(line: Int, columns: Int): Int {
        return line % columns
    }

    fun err(s: String) {
        Gdx.app.error("ERROR", s)
    }

}