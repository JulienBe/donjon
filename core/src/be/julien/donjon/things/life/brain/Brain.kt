package be.julien.donjon.things.life.brain

abstract class Brain(n: Int) {

    abstract fun decide(array: FloatArray): Int

}