package be.julien.donjon.physics;

import org.jetbrains.annotations.NotNull;

public enum Mask {

    Life(Val.life, Val.life, Val.wall, Val.energy),
    Wall(Val.wall),
    Energy(Val.energy),
    Sensor(Val.sensor, Val.life, Val.wall, Val.energy);

    byte flag;
    byte colliders;

    Mask(byte flag, byte... others) {
        this.flag = flag;
        for (byte other : others) {
            colliders |= other;
        }
        System.out.println("" + this.flag + " with : " + colliders);
    }

    public boolean collidesWith(@NotNull Mask other) {
        return (other.colliders & flag) == flag;
    }

}

class Val {
    static final byte life = 0x1;
    static final byte wall = 0x2;
    static final byte energy = 0x4;
    static final byte sensor = 0x8;
}