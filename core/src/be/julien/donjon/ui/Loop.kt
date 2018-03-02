package be.julien.donjon.ui

import be.julien.donjon.Globals
import be.julien.donjon.handlers.GdxInputHandler
import be.julien.donjon.world.World
import com.badlogic.gdx.Gdx

class Loop : MyScreen() {

    private var world: World = World(Globals.input)

    override fun render(delta: Float) {
//        println("\t" + Gdx.graphics.frameId)
        Globals.input.act(GdxInputHandler)
        world.act(Gdx.graphics.deltaTime)
        Globals.drawer.batch(world::draw)
    }

    override fun dispose() {
        Globals.drawer.dispose()
    }

}
