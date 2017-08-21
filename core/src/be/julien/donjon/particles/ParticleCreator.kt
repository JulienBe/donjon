package be.julien.donjon.particles

import be.julien.donjon.hubs.HubClient
import be.julien.donjon.hubs.LifeHub
import be.julien.donjon.things.life.Life

class ParticleCreator: HubClient {

    init {
        LifeHub.add(this)
    }

    override fun lifeCreated(life: Life) {
        for (i in 0..life.initEnergy) {
            ParticleMoving.spawn(life.rndX(), life.rndY())
        }
    }

}