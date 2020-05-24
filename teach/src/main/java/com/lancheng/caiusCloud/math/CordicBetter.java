package com.lancheng.caiusCloud.math;

public class CordicBetter {

    static final int iterations = 32;

    static double[] ctab = new double[iterations];

    public static void Table_Generate() {
        double theta = 1.0;
        for (int i = 0; i < iterations; i++) {
            ctab[i] = Math.atan(theta);
//            System.out.println(ctab[i]*180/Math.PI);
//            System.out.printf("Cordic table %d is arctan %.17f = %.17f\n", i, theta, ctab[i]);
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
        Table_Generate();
        double[] tests = { Math.PI / 2.0, Math.PI / 3.0, Math.PI / 4.0,Math.PI / 6.0};
        for (int i = 0; i < tests.length; i++) {
            double theta = tests[i];
            double answer = cordic(theta) / 1e9;
            System.out.printf("Cordic %f is %f \n", theta, answer);
        }
    }

}
