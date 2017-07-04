package be.julien.donjon

import be.julien.donjon.world.World
import com.badlogic.gdx.ApplicationAdapter

class DonjonMain : ApplicationAdapter() {

    internal val world = World()
    lateinit internal var drawer: Drawer

    override fun create() {
        drawer = Drawer()
    }

    override fun render() {
        world.act()
        drawer.batch(world::draw)
    }

    override fun dispose() {
        drawer.dispose()
    }
}
