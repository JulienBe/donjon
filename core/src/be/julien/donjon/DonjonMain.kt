package be.julien.donjon

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.inputs.InputHub
import be.julien.donjon.physics.b2d.BoxWorld
import be.julien.donjon.world.World
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input

class DonjonMain : ApplicationAdapter() {

    internal val world = World()
    internal val input = InputHub()
    lateinit internal var drawer: Drawer

    override fun create() {
        drawer = Drawer()
        Gdx.input.inputProcessor = input
        input.addInput(Input.Keys.SPACE, { world.spawn()})
    }

    override fun render() {
        world.act(Gdx.graphics.deltaTime)
        BoxWorld.act(Gdx.graphics.deltaTime)
        drawer.batch(world::draw)
        BoxWorld.render(drawer)
    }

    override fun dispose() {
        drawer.dispose()
    }
}
