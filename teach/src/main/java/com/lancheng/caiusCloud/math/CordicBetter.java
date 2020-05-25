package com.lancheng.caiusCloud.math;

public class CordicBetter {

    static final int iterations = 32;

    static double[] ctab = new double[iterations];

    private static void tableGenerate() {
        double theta = 1.0;
        for (int i = 0; i < iterations; i++) {
            ctab[i] = Math.atan(theta);
            theta = theta / 2.0;
        }
    }

    public static double cordic(double theta) {
        int x = 607252935 , y = 0;
        for (int k = 0; k < iterations; ++k) {
            int tx, ty;
            if (theta >= 0)
            {
                tx = x - (y >> k);
                ty = y + (x >> k);
                theta -= ctab[k];
            } else {
                tx = x + (y >> k);
                ty = y - (x >> k);
                theta += ctab[k];
            }
            x = tx;
            y = ty;
        }
        return  x; // Returns cosine * 10^9.
    }

    public static void main(String[] args) {
        tableGenerate();
        double[] tests = { Math.PI / 2.0, Math.PI / 3.0, Math.PI / 4.0,Math.PI / 6.0};
        for (double theta : tests) {
            double answer = cordic(theta) / 1e9;
            System.out.printf("Cordic %f is %f \n", theta, answer);
        }
    }

}
