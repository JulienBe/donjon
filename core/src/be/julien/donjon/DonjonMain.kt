package be.julien.donjon

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.hubs.InputHub
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
        world = World(input)
        Gdx.input.inputProcessor = input
        input.addKeyUp(Input.Keys.SPACE, { world.spawn() })
    }

    override fun render() {
        input.act()
        world.act(Gdx.graphics.deltaTime)
        drawer.batch(world::draw)
    }

    override fun dispose() {
        drawer.dispose()
    }
}
