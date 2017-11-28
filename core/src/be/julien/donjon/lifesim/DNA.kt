package be.julien.donjon.lifesim

import be.julien.seed.Rnd

class DNA(val genes: Array<Float> = Array(10){Rnd.float(2f)}) {
    fun mix(dna: DNA): DNA {
        val a = Array(genes.size, {
            i ->
            if (Rnd.bool()) mutation(genes[i]) else mutation(dna.genes[i])
        })
        genes.forEach { print("" + it + ", ") }
        return DNA(a)
    }

    companion object {
        val mutation = 0.2f
        val halfMutation = mutation / 2f

        fun mutation(gene: Float): Float {
            return gene + (halfMutation - Rnd.float(mutation))
        }
    }
}