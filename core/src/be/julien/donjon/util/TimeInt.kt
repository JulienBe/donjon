package be.julien.donjon.util

class TimeInt(internal var value: Int, private var interval: Float = 1f, internal var increment: Int) {

    private var nextTrigger: Float = Time.time + interval
    private var callback: Callback? = null

    fun act() {
        if (nextTrigger < Time.time) {
            nextTrigger = Time.time + interval
            value += increment
            callback?.check(this)
            Util.out("" + value + " - " + Time.time + " " + interval)
        }
    }

    fun setCallback(callbackValue: Int, method: () -> Unit) {
        callback = CallbackComp.get(callbackValue, method)
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

