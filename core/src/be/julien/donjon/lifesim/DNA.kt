package be.julien.donjon.lifesim

import be.julien.donjon.util.Rnd

class DNA(val genes: Array<Float> = Array(10){1f}) {
    fun mix(dna: DNA): DNA {
        val a = Array<Float>(genes.size, {
            i ->
            if (Rnd.bool()) genes[i] + Rnd.float(mutation) else dna.genes[i] + Rnd.float(mutation)
        })
        return DNA(a)
    }

    companion object {
        val mutation = 0.05f
    }
}