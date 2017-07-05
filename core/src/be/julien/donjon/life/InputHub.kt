package be.julien.donjon.life

import be.julien.donjon.Util
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter

class InputHub : InputAdapter() {

    internal val inputs: MutableMap<Int, () -> Unit> = mutableMapOf()

    override fun keyUp(keycode: Int): Boolean {
        inputs.put(Input.Keys.SPACE, this::test)
        inputs.forEach {
            key, value ->
            if (keycode == key)
                value.invoke()
        }
        return true
    }

    fun test() {
        Util.out("test")
    }

    fun addInput(key: Int, function: () -> Unit) {
        inputs.put(key, function)
    }
}
