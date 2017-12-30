package be.julien.donjon

import be.julien.donjon.handlers.GdxInputHandler
import be.julien.donjon.world.World
import be.julien.seed.utils.InputHub
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx

class Loop : ApplicationAdapter() {

    private lateinit var world: World
    private val input = InputHub()
    lateinit internal var drawer: GdxDrawer

    /**
     * Init is deferred here so libGDX has done its init properly
     */
    override fun create() {
        drawer = GdxDrawer()
        world = World(input)
    }

    override fun render() {
        println("\t" + Gdx.graphics.frameId)
        input.act(GdxInputHandler)
        world.act(Gdx.graphics.deltaTime)
        drawer.batch(world::draw)
    }

    override fun dispose() {
        drawer.dispose()
    }
}
