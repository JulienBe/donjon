package be.julien.donjon

import com.badlogic.gdx.Gdx

/**
 * Created by julien on 04/07/17.
 */

object Utility {
    fun out(string: String) {
        Gdx.app.log("Out", string.toString())
    }
}