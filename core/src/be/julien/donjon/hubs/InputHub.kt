package be.julien.donjon.hubs

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter

class InputHub : InputAdapter() {

    internal val keyUpMapping: MutableMap<Int, () -> Unit> = mutableMapOf()
    internal val keyPressedMapping: MutableMap<Int, () -> Unit> = mutableMapOf()

    override fun keyUp(keycode: Int): Boolean {
        checkKey(keycode, keyUpMapping)
        return true
    }

    fun act() {
        keyPressedMapping.forEach { key, value ->
            if (Gdx.input.isKeyPressed(key))
                value.invoke()
        }
    }

    fun addKeyUp(key: Int, function: () -> Unit) {
        keyUpMapping.put(key, function)
    }
    fun addKeyPressed(key: Int, function: () -> Unit) {
        keyPressedMapping.put(key, function)
    }

    fun checkKey(keycode: Int, mapping: MutableMap<Int, () -> Unit>) {
        mapping.forEach {
            key, value ->
            if (keycode == key)
                value.invoke()
        }
    }
}
