package be.julien.donjon.things.life.brain

import be.julien.donjon.util.Rnd

class Perceptron(n: Int) : Brain(n) {

    var weights = Array(n) { Rnd.float() }

    override fun decide(array: FloatArray): Int {
        return feedforward(array)
    }

    fun feedforward(inputs: FloatArray): Int {
        val sum = (0..weights.size - 1)
                .map { inputs[it] * weights[it] }
                .sum()
        return activate(sum)
    }

    fun activate(sum: Float): Int {
        if (sum > 0)
            return 1
        return -1
    }
}