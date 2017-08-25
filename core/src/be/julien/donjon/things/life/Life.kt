package be.julien.donjon.things.life

import be.julien.donjon.GdxArr
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.hubs.Hub
import be.julien.donjon.lifesim.DNA
import be.julien.donjon.physics.Mask
import be.julien.donjon.physics.shapes.Shape
import be.julien.donjon.physics.shapes.SquareAO
import be.julien.donjon.util.spatial.Vec2
import be.julien.donjon.things.Energy
import be.julien.donjon.things.Thing
import be.julien.donjon.things.WallAO
import be.julien.donjon.util.time.TimeInt
import com.badlogic.gdx.graphics.Color

abstract class Life(pos: Vec2 = Vec2.getRandWorld(), dir: Vec2 = Vec2.getRandWorld(), val dna: DNA = DNA()): Thing(pos, dir) {

    val initEnergy = 20
    internal var energy = initEnergy()
    private val canReproduce = TimeInt.get(0, 1f, 1)
    open internal fun energyStealVal(): Int = 1

    init {
        norSpeed()
    }

    override fun shape(): Shape = SquareAO

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
        drawer.color(Color.GRAY)
        super.draw(drawer)
    }

    override fun collidesWith(thing: Thing) {
        when (thing) {
            is Energy -> energy.add(thing.stealEnergy(energyStealVal()))
            is WallAO -> energy.step(2)
            is Life -> {
                Vec2.tmp.set(dir.x() + thing.dir.y(), dir.y() + thing.dir.x()).nor()
                dir.add(Vec2.tmp)
            }
        }
    }

    override fun viscosity(a: Thing): Float {
        if (a is Life)
            return 0.1f
        return 0.8f
    }

    override fun angle(): Float = dir.angle()
    override fun mask(): Mask = Mask.Life

    companion object {
        fun mostBasic(dir: Vec2 = Vec2.getRnd()): MostBasic {
            dir.nor()
            return created(MostBasic(Vec2.getRandWorld(), dir)) as MostBasic
        }

        fun mostBasic(excluded: GdxArr<Thing>): MostBasic {
            return created(MostBasic(Vec2.getRandWorld(excluded), Vec2.getRnd())) as MostBasic
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