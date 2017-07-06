package be.julien.donjon.inputs

import com.badlogic.gdx.InputAdapter

class InputHub : InputAdapter() {

    internal val inputs: MutableMap<Int, () -> Unit> = mutableMapOf()

    override fun keyUp(keycode: Int): Boolean {
        inputs.forEach {
            key, value ->
            if (keycode == key)
                value.invoke()
        }
        return true
    }

    fun addInput(key: Int, function: () -> Unit) {
        inputs.put(key, function)
    }
}
