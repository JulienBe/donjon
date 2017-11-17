package be.julien.donjon.hubs

import be.julien.donjon.things.life.Life
import be.julien.donjon.things.player.Bullet

interface HubClient {
    fun lifeCreated(life: Life) {}
    fun bulletCreated(b: Bullet) {}
    fun bulletRemoved(bullet: Bullet) {}
}