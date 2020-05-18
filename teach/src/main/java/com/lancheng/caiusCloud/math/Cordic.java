package com.lancheng.caiusCloud.math;

/**
 * 该方法是测试cordic算法实现三角函数计算的测试类
 */
public class Cordic {

    public static void main(String[] args) {

         double para;
         para=30d;
         double result = cordic(para);
        }

    static double cordic(double angle_para) {
        double[] tangent = {1.0, 1 / 2.0, 1 / 4.0, 1 / 8.0, 1 / 16.0, 1 / 32.0, 1 / 64.0, 1 / 128.0,
                1 / 512.0};
        double[] angle = {45.0, 26.6, 14, 7.1, 3.6, 1.8, 0.9, 0.4, 0.2, 0.1};
        int i, signal;
        double x_cos, y_sin, x_temp, y_temp, z, z_next;
        x_cos = 0;
        y_sin = 0;
        z = angle_para;
        z_next = 0;
        x_temp = 0.6073;
        y_temp = 0;
        signal = 1;
        for (i = 0; i < 9; i++) {
            x_cos = x_temp - signal * y_temp * tangent[i];
            y_sin = y_temp + signal * x_temp * tangent[i];
            z_next = z - signal * angle[i];

            x_temp = x_cos;
            y_temp = y_sin;
            z = z_next;
            if (z_next > 0) {
                signal = +1;
            } else {
                signal = -1;
            }
        }
        System.out.println("x_cos =" + x_cos + "y_sin=" + y_sin);
        return 0;
    }
}