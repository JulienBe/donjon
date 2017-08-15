package be.julien.donjon.util

class TimeInt(internal var value: Int, private var interval: Float = 1f, internal var increment: Int) {

    private var nextTrigger: Float = Time.time + interval
    private var callback: Callback? = null
    private val initialVal = value

    fun act() {
        if (nextTrigger < Time.time) {
            nextTrigger = Time.time + interval
            step()
        }
    }

    fun step() {
        step(1)
    }

    fun step(steps: Int) {
        value += increment * steps
        callback?.check(this)
    }

    fun setCallback(callbackValue: Int, method: () -> Unit) {
        callback = CallbackComp.get(callbackValue, method)
    }

    fun add(i: Int) {
        value += i
    }

    fun reset() {
        value = initialVal
        nextTrigger = Time.time + interval
    }
}

object TimeIntComp {
    fun get(value: Int, interval: Float, increment: Int): TimeInt {
        return TimeInt(value, interval, increment)
    }
}


internal class Callback(var callbackValue: Int, var callback: () -> Unit) {
    internal var triggered = false
    fun check(timeFloat: TimeInt) {
        if (!triggered && callbackValue == timeFloat.value)
            activate()
    }

    private fun activate() {
        triggered = true
        callback.invoke()
    }
}
internal object CallbackComp {
    fun get(callbackValue: Int, method: () -> Unit): Callback {
        return Callback(callbackValue, method)
    }
}

