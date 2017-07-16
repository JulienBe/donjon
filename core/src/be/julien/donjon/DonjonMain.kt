package be.julien.donjon

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.inputs.InputHub
import be.julien.donjon.physics.b2d.BoxWorld
import be.julien.donjon.world.World
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input

class DonjonMain : ApplicationAdapter() {

    internal lateinit var world: World
    internal val input = InputHub()
    lateinit internal var drawer: Drawer

    override fun create() {
        drawer = Drawer()
        world = World()
        Gdx.input.inputProcessor = input
        input.addInput(Input.Keys.SPACE, { world.spawn() })
        input.addInput(Input.Keys.D, { world.debug() })
    }

    override fun render() {
        world.act(Gdx.graphics.deltaTime)
        BoxWorld.act(Gdx.graphics.deltaTime)
        drawer.batch(world::draw)
//        BoxWorld.render(drawer)
    }

    override fun dispose() {
        drawer.dispose()
    }
}
