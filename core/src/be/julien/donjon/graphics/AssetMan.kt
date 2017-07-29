package be.julien.donjon.graphics

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetErrorListener
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion

class AssetMan : AssetErrorListener {
    private val man = AssetManager()
    //    private val atlas: TextureAtlas = TextureAtlas(Gdx.files.internal(atlas))

    init {
        man.setErrorListener(this)
        Texture.setAssetManager(man)
        man.load(atlas, TextureAtlas::class.java)
        while (!man.update(20)) {
            println("" + System.currentTimeMillis() + " : " + man.progress)
        }
        val atlas = man.get(atlas, TextureAtlas::class.java)
        square = atlas.findRegion("loop")
        circle = atlas.findRegion("bullet")
    }

    override fun error(asset: AssetDescriptor<*>, throwable: Throwable) {
        println("Probleme pour asset " + asset + " -------- " + throwable.message)
    }

    companion object {
        private val atlas = "textures.atlas"
        lateinit var square: TextureRegion
        lateinit var circle: TextureRegion
    }

}

