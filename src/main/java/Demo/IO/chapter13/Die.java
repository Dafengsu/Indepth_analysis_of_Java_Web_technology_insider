package Demo.IO.chapter13;

import java.util.Random;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/10 19:07
 */
public class Die {
    private int face = 1;
    Random shooter = new Random();

    public Die(int face) {
        if (face < 1 || face > 6) {
            throw new IllegalArgumentException();
        }
        this.face = face;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        if (face < 1 || face > 6) {
            throw new IllegalArgumentException();
        }
        this.face = face;
    }

    public int roll() {
        this.face = (Math.abs(shooter.nextInt()) % 6) + 1;
        return this.face;
    }
}
