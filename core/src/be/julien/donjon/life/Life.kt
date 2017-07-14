package be.julien.donjon.life

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.physics.b2d.BoxHelper
import be.julien.donjon.spatial.Mover
import be.julien.donjon.spatial.Rect
import be.julien.donjon.spatial.Vec2
import be.julien.donjon.util.Rnd
import be.julien.donjon.util.TimeInt
import be.julien.donjon.util.TimeIntComp
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.utils.Pool

abstract class Life(rect: Rect, dir: Vec2 = Vec2.getRandWorld()): Mover(rect, dir), Pool.Poolable {

    internal var energy = initEnergy()
    internal val body = BoxHelper.createRectangle(BodyDef.BodyType.KinematicBody, rect)
    var dead = false

    private fun initEnergy(): TimeInt {
        val e = TimeIntComp.get(100, 1f, -1)
        e.setCallback(0, this::noEnergy)
        return e
    }

    override fun reset() {
        energy = initEnergy()
    }

    private fun noEnergy(): Unit {
        dead = true
    }

    override fun act(delta: Float) {
        super.act(delta)
        body.setPos(rect)
        energy.act()
    }

    fun draw(drawer: Drawer) {
        drawer.color(Color.YELLOW)
        drawer.drawAbsolute(rect)
    }

    fun collidesWith(any: Any) {
        println("collision life")
        energy.step()
    }

    companion object {
        fun mostBasic(rect: Rect = Rect.rndPos(1f, 1f), dir: Vec2 = Vec2.getRandWorld(), right: Float = Rnd.float(), left: Float = Rnd.float(), forward: Float = Rnd.float()): MostBasic {
            dir.nor()
            return MostBasic(rect, dir, right, left, forward)
        }
    }
}