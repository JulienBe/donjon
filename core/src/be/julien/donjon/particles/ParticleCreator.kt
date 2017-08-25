package be.julien.donjon.particles

import be.julien.donjon.hubs.HubClient
import be.julien.donjon.hubs.Hub
import be.julien.donjon.things.life.Life

class ParticleCreator: HubClient {

    init {
        Hub.addLifeListener(this)
    }

    override fun lifeCreated(life: Life) {
        for (i in 0..life.initEnergy) {
            ParticleMoving.spawn(life.rndX(), life.rndY())
        }
    }

}