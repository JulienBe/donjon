package be.julien.donjon.things.life

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.physics.Mask
import be.julien.donjon.physics.b2d.BoxHelper
import be.julien.donjon.spatial.Mover
import be.julien.donjon.spatial.Rect
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.things.Energy
import be.julien.donjon.things.Thing
import be.julien.donjon.util.TimeInt
import be.julien.donjon.util.TimeIntComp
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.utils.Pool

abstract class Life(rect: Rect, dir: Vec2 = Vec2.getRandWorld()): Mover(rect, dir), Pool.Poolable {

    internal var energy = initEnergy()
    internal val body = BoxHelper.createRectangle(BodyDef.BodyType.KinematicBody, rect)

    private fun initEnergy(): TimeInt {
        val e = TimeIntComp.get(100, 1f, -1)
        e.setCallback(0, this::noEnergy)
        return e
    }

    override fun reset() {
        energy = initEnergy()
    }

    private fun noEnergy(): Unit {
        die()
    }

    override fun act(delta: Float): Boolean {
        body.setPos(rect)
        energy.act()
        return super.act(delta)
    }

    override fun draw(drawer: Drawer) {
        drawer.color(Color.YELLOW)
        super.draw(drawer)
    }

    override fun collidesWith(thing: Thing) {
        when (thing) {
            is Energy -> energy.add(thing.getEnergy())
            else -> energy.step()
        }
    }

    override fun angle(): Float = dir.angle()

    override fun mask(): Mask = Mask.Life

    companion object {
        fun mostBasic(rect: Rect = Rect.rndPos(2f, 2f), dir: Vec2 = Vec2.getRnd()): MostBasic {
            dir.nor()
            return MostBasic(rect, dir)
        }
    }
}