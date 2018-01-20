package be.julien.donjon

import be.julien.donjon.handlers.GdxInputHandler
import be.julien.donjon.world.World
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx

class Loop : ApplicationAdapter() {

    private lateinit var world: World

    override fun create() {
        world = World(Globals.input)
    }

    override fun render() {
        println("\t" + Gdx.graphics.frameId)
        Globals.input.act(GdxInputHandler)
        world.act(Gdx.graphics.deltaTime)
        Globals.drawer.batch(world::draw)
    }

    override fun dispose() {
        Globals.drawer.dispose()
    }
}
