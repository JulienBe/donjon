package be.julien.donjon.hubs

import be.julien.donjon.things.life.Life

interface HubClient {
    fun lifeCreated(life: Life)
}