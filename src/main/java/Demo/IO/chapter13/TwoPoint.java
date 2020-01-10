package Demo.IO.chapter13;

import java.beans.Beans;
import java.io.*;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/9 21:16
 */
public class TwoPoint {
    private double x;
    private double y;

    public TwoPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "TwoPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }


}
