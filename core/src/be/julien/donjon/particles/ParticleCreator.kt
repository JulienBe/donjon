package be.julien.donjon.particles

import be.julien.donjon.hubs.HubClient
import be.julien.donjon.hubs.Hub
import be.julien.donjon.things.life.Life
import be.julien.donjon.things.player.Bullet

class ParticleCreator: HubClient {

    init {
        Hub.addLifeListener(this)
        Hub.addBulletListener(this)
    }

    override fun lifeCreated(life: Life) {
        for (i in 0..life.initEnergy)
            ParticleMoving.spawn(life.rndX(), life.rndY())
    }

    override fun bulletCreated(b: Bullet) {
        for (i in 0..40) {
            ParticleSpark.spawn(b.centerX(), b.centerY(), b.dir)
        }
    }

}