package be.julien.donjon.spatial

enum class Direction(val x: Int, val y: Int) {
    right(1, 0) {
        override fun steerLeft(): be.julien.donjon.spatial.Direction = be.julien.donjon.spatial.Direction.up
        override fun steerRight(): be.julien.donjon.spatial.Direction = be.julien.donjon.spatial.Direction.down
    },
    up(0, 1) {
        override fun steerLeft(): be.julien.donjon.spatial.Direction = be.julien.donjon.spatial.Direction.left
        override fun steerRight(): be.julien.donjon.spatial.Direction = be.julien.donjon.spatial.Direction.right
    },
    left(-1, 0) {
        override fun steerLeft(): be.julien.donjon.spatial.Direction = be.julien.donjon.spatial.Direction.down
        override fun steerRight(): be.julien.donjon.spatial.Direction = be.julien.donjon.spatial.Direction.up
    },
    down(0, -1) {
        override fun steerLeft(): be.julien.donjon.spatial.Direction = be.julien.donjon.spatial.Direction.right
        override fun steerRight(): be.julien.donjon.spatial.Direction = be.julien.donjon.spatial.Direction.left
    };

    abstract fun steerLeft(): be.julien.donjon.spatial.Direction
    abstract fun steerRight(): be.julien.donjon.spatial.Direction
}