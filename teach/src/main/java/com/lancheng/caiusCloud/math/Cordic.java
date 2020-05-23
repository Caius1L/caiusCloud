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
                1 / 512.0, 1 / 1024.0, 1 / 2048.0, 1 / 4096.0, 1 / 8192.0, 1 / 16384.0, 1 / 32768.0,
                1 / 65536.0, 1 / 131072.0, 1 / 262144.0, 1 / 524288.0, 1 / 1048576.0};
        double[] angle = {45.0000000000000000, 26.5650511770779900, 14.0362434679264787, 7.1250163489017977,
                3.5763343749973511, 1.7899106082460694, 0.8951737102110744, 0.4476141708605531, 0.2238105003685381,
                0.1119056770662069, 0.0559528918938037, 0.0279764526170037, 0.0139882271422650, 0.0069941136753529,
                0.0034970568507040, 0.0017485284269804, 0.0008742642136938, 0.0004371321068723, 0.0002185660534393,
                0.0001092830267201, 0.0000546415133601, 0.0000273207566800, 0.0000136603783400, 0.0000068301891700,
                0.0000034150945850};
        int i, signal;
        double x_cos, y_sin, x_temp, y_temp, z, z_next;
        x_cos = 0;
        y_sin = 0;
        z = angle_para;
        z_next = 0;
        x_temp = 0.6072529350088816;
        y_temp = 0;
        signal = 1;
        for (i = 0; i < 20; i++) {
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