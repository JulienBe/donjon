package be.julien.donjon.physics.b2d

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body

class BoxBody(val body: Body) {

    fun setPos(v2: Vector2) {
        body.setTransform(v2, 0f)
    }

}