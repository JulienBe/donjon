package be.julien.donjon

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.life.InputHub
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
        input.addInput(Input.Keys.SPACE, { println("Test")})
    }

    override fun render() {
        world.act()
        drawer.batch(world::draw)
    }

    override fun dispose() {
        drawer.dispose()
    }
}
