package be.julien.donjon

import be.julien.donjon.graphics.Drawer
import be.julien.donjon.handlers.GdxInputHandler
import be.julien.donjon.world.World
import be.julien.seed.InputHub
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx

class Loop : ApplicationAdapter() {

    private lateinit var world: World
    private val input = InputHub()
    lateinit internal var drawer: Drawer

    /**
     * Init is deferred here so libGDX has done its init properly
     */
    override fun create() {
        drawer = Drawer()
        world = World(input)
    }

    override fun render() {
        input.act(GdxInputHandler)
        world.act(Gdx.graphics.deltaTime)
        drawer.batch(world::draw)
    }

    override fun dispose() {
        drawer.dispose()
    }
}
