package com.lancheng.caiusCloud.DesignPattern.Adapter;

/**
 * 圆孔
 */
public class RoundHole {

    private double radius;

    public RoundHole(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public boolean fits(RoundPeg roundHPeg) {
        boolean result;
        result = (this.getRadius() >= roundHPeg.getRadius());
        return result;
    }
}
