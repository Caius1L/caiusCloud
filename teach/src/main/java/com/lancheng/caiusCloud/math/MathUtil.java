package com.lancheng.caiusCloud.math;

public class MathUtil {

    private static final float PI = 3.141592658f;

    //绝对值
    double abs(double n) {
        if (n < 0) {
            n *= (-1);
        }
        return n;
    }
    //利用麦克劳林公式
    static int calculate(int angle) {
        int k = 0, i = 0, j = 0, cons = 1;
        float x = 0, s = 0, c = 0, sin = 0, cos = 0, tan = 0;     //用y将弧度制转换为角度制
        x = angle * PI / 180;
        for (k = 1; k <= 11; k++) {
            i = 2 * k - 1;
            j = 2 * k - 2;
            s = cons * pwr(x, i) / fac(i);
            c = cons * pwr(x, j) / fac(j);
            cons = -1 * cons;
            sin = sin + s;
            cos = cos + c;
        }
        tan = sin / cos;
        System.out.println("sin:" + sin);
        System.out.println("cos:" + cos);
        System.out.println("tan:" + tan);
        return 0;
    }

    static float pwr(float x, int n) {
        int i = 0;
        float powers = 1;
        if (n == 0) {
            return 1;
        }
        for (i = 1; i <= n; i++) {
            powers = powers * x;
        }
        return powers;
    }

    static float fac(int n) {
        int i = 0;
        float pdt = 1;
        if (n == 0 || n == 1) {
            return 1;
        }
        for (i = 2; i <= n; i++) {
            pdt = pdt * i;
        }
        return pdt;
    }

    public static void main(String[] args) {
        MathUtil.calculate(60);
    }

    //todo 还没有理解清楚
//    //正弦
//    double sin(double x) {
//        int i = 1, sign = 1;
//        double item = x, frac = 0, fz = x, fm = 1;
//        for (; abs(item) >= 10E-5; i += 2) {
//            frac += item;//累加
//            fz = fz * x * x;//分子
//            fm = fm * (i + 1) * (i + 2);//分母
//            sign = -sign;//符号
//            item = sign * (fz / fm);//临时变量
//        }
//        return (frac);
//    }
//
//    //余弦
//    double cos(double x) {
//        int i = 0, sign = 1;
//        double item = 1, frac = 0, fz = 1, fm = 1;
//        for (; abs(item) >= 10E-5; i += 2) {
//            frac += item;
//            fz = fz * x * x;
//            fm = fm * (i + 1) * (i + 2);
//            sign = -sign;
//            item = sign * (fz / fm);
//        }
//        return (frac);
//    }

}
