package be.julien.donjon.graphics;

import com.badlogic.gdx.utils.NumberUtils;

public class Color {
    private static float WHITE = convertARGB(1f, 1f, 1f, 1f);
    private static float ALPHA40 = convertARGB(.4f, 1f, 1f, 1f);
    private static float BLACK = convertARGB(1f, 0f, 0f, 0f);
    private static float ALPHA70 = convertARGB(.70f, 1f, 1f, 1f);
    private static final short MAX = 255, A = 24, R = 16, G = 8;

    public static float convertARGB(float a, float r, float g, float b) {
        return NumberUtils.intToFloatColor(((int)(MAX * a) << A) | ((int)(MAX * b) << R) | ((int)(MAX * g) << G) | ((int)(MAX * r)));
    }

    public static Float convertARGB(float a, float all) {
        return NumberUtils.intToFloatColor(((int)(MAX * a) << A) | ((int)(MAX * all) << R) | ((int)(MAX * all) << G) | ((int)(MAX * all)));
    }

    public static int tmpInt;
    public static float setAlpha(float color, float alpha) {
        tmpInt = NumberUtils.floatToIntColor(color);
        return convertARGB(alpha, (tmpInt & 0xff) / 255f, ((tmpInt >>> 8) & 0xff) / 255f, ((tmpInt >>> 16) & 0xff) / 255f);
    }
    public static float setBlue(float color, float blue) {
        tmpInt = NumberUtils.floatToIntColor(color);
        return convertARGB((tmpInt & 0xff) / 255f, (tmpInt & 0xff) / 255f, ((tmpInt >>> 8) & 0xff) / 255f, blue);
    }

}
