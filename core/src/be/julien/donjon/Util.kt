package be.julien.donjon

import com.badlogic.gdx.Gdx
import java.util.*

object Util {

    val rnd = Random()

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