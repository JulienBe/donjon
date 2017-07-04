package be.julien.donjon

import com.badlogic.gdx.Gdx

/**
 * Created by julien on 04/07/17.
 */

object Util {
    fun out(string: String) {
        Gdx.app.log("Out", string.toString())
    }

    fun lineToRow(line: Int, columns: Int): Int {
        return line / columns
    }

    fun lineToCol(line: Int, columns: Int): Int {
        return line % columns
    }
}