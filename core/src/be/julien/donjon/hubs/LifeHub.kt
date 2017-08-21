package be.julien.donjon.hubs

import be.julien.donjon.GdxArr
import be.julien.donjon.things.life.Life

object LifeHub {

    private val listeners = GdxArr<HubClient>()

    fun add(c: HubClient) {
        listeners.add(c)
    }

    fun lifeCreation(life: Life) {
        listeners.forEach { it.lifeCreated(life) }
    }

}