package com.lancheng.caiusCloud.DesignPattern.Adapter;

/**
 * 方钉到圆钉的适配器
 */
public class SquarePegAdapter extends RoundPeg {

    private SquarePeg squarePeg;

    public SquarePegAdapter(SquarePeg squarePeg) {
        this.squarePeg = squarePeg;
    }

    @Override
    public double getRadius() {
        double result;
        result = (Math.sqrt(Math.pow(squarePeg.getSquare() / 2, 2) * 2));
        return result;
    }
}
