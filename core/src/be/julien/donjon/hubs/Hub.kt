package be.julien.donjon.hubs

import be.julien.donjon.GdxArr
import be.julien.donjon.things.life.Life
import be.julien.donjon.things.player.Bullet

object Hub {

    private val lifeListeners = GdxArr<HubClient>()
    private val bulletListeners = GdxArr<HubClient>()

    fun addLifeListener(c: HubClient) {
        lifeListeners.add(c)
    }

    fun addBulletListener(c: HubClient) {
        bulletListeners.add(c)
    }

    fun lifeCreation(life: Life) {
        lifeListeners.forEach { it.lifeCreated(life) }
    }

    fun bulletCreation(b: Bullet) {
        bulletListeners.forEach { it.bulletCreated(b) }
    }

}