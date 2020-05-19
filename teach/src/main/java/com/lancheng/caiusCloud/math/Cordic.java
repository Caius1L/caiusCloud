package com.lancheng.caiusCloud.math;

/**
 * 该方法是测试cordic算法实现三角函数计算的测试类
 */
public class Cordic {

    public static void main(String[] args) {

        double para;
        para = 30d;
        double result = cordic(para);
        double r = Math.PI / 6;
        System.out.println(Math.sin(r));
    }

    static double cordic(double angle_para) {
        double[] tangent = {1.0, 1 / 2.0, 1 / 4.0, 1 / 8.0, 1 / 16.0, 1 / 32.0, 1 / 64.0, 1 / 128.0,
                1 / 512.0, 1 / 1024.0, 1 / 2048.0, 1 / 4096.0, 1 / 8192.0, 1 / 16384.0, 1 / 32768.0};
        double[] angle = {45.0, 26.56505, 14.03624, 7.12501, 3.57633, 1.78991, 0.89517, 0.44761, 0.22381, 0.1119,
                0.05595, 0.02797, 0.01398, 0.00699, 0.00349, 0.00174};
        int i, signal;
        double x_cos, y_sin, x_temp, y_temp, z, z_next;
        x_cos = 0;
        y_sin = 0;
        z = angle_para;
        z_next = 0;
        x_temp = 0.607253;
        y_temp = 0;
        signal = 1;
        for (i = 0; i < 15; i++) {
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