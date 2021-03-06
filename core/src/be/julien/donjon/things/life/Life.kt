package be.julien.donjon.things.life

import be.julien.donjon.graphics.AssetMan
import be.julien.donjon.GdxDrawer
import be.julien.donjon.hubs.Hub
import be.julien.donjon.lifesim.DNA
import be.julien.donjon.things.Energy
import be.julien.seed.basics.Thing
import be.julien.seed.basics.WallAO
import be.julien.seed.graphics.Color
import be.julien.seed.graphics.Drawer
import be.julien.seed.physics.Mask
import be.julien.seed.physics.Vec2
import be.julien.seed.physics.shapes.Shape
import be.julien.seed.physics.shapes.SquareAO
import be.julien.seed.time.TimeInt

abstract class Life(
        pos: Vec2 = Vec2.getRandWorld(10f, 10f), dir: Vec2 = Vec2.getRandWorld(10f, 10f),
        val dna: DNA = DNA()): Thing(pos, dir) {

    val initEnergy = 20
    internal var energy = initEnergy()
    private val canReproduce = TimeInt.get(0, 1f, 1)
    open internal fun energyStealVal(): Int = 1
    override val mask: Mask
        get() = Mask.Life
    override val shape: Shape
        get() = SquareAO
    override val img: Any
        get() = AssetMan.square

    init {
        norSpeed()
    }

    internal fun norSpeed() {
        dir.nor().scl(10f)
    }

    private fun initEnergy(): TimeInt {
        val e = TimeInt.get(initEnergy, 1f, -1)
        e.setCallback(0, this::noEnergy)
        return e
    }

    open internal fun canReproduce(): Boolean {
        return canReproduce.value > 5 && energy.value > initEnergy
    }

    open internal fun reproduce(life: Life) {
        energy.step(initEnergy)
        canReproduce.reset()
    }

    private fun noEnergy(): Unit {
        die()
    }

    override fun act(delta: Float): Boolean {
//        Particle.spawn(pos.x + w() * Rnd.float(), pos.y + h() * Rnd.float(), energy.value)
        energy.act()
        canReproduce.act()
        return super.act(delta)
    }

    override fun draw(drawer: Drawer) {
        drawer.color(Color.WHITE)
        super.draw(drawer)
    }

    override fun collidesWith(thing: Thing) {
        when (thing) {
            is Energy -> energy.add(thing.stealEnergy(energyStealVal()))
            is WallAO -> energy.step(2)
            is Life -> {
                Vec2.tmp.set(dir.x + thing.dir.y, dir.y + thing.dir.x).nor()
                dir.add(Vec2.tmp)
            }
        }
    }

    override fun viscosity(a: Thing): Float {
        if (a is Life)
            return 0.1f
        return 0.8f
    }

    companion object {
        fun mostBasic(dir: Vec2 = Vec2.getRnd()): MostBasic {
            dir.nor()
            return created(MostBasic(Vec2.getRandWorld(GdxDrawer.screenWidth, GdxDrawer.screenHeight), dir)) as MostBasic
        }

        fun mostBasic(excluded: Iterable<Thing>): MostBasic {
            return created(MostBasic(Vec2.getRandWorld(excluded, GdxDrawer.screenWidth, GdxDrawer.screenHeight), Vec2.getRnd())) as MostBasic
        }

        fun mostBasic(pos: Vec2, dir: Vec2, mix: DNA): MostBasic {
            return created(MostBasic(pos, dir, mix)) as MostBasic
        }

        private fun created(l: Life): Life {
            Hub.lifeCreation(l)
            return l
        }
    }
}