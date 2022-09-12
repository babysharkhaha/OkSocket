package com.aa.lib33;

import java.math.BigDecimal;

public class TestEight {
//    float a = 1.0f - 0.9f;
//    float b = 0.9f - 0.8f;
//    float diff = 1e-6f;
//
//    if (Math.abs(a - b) < diff) {
//        System.out.println("true");
//    }

    public static void main(String[] args) {
        testBigDicemal();
    }

    private static void testBigDicemal() {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");
        BigDecimal d = a.subtract(b);
        BigDecimal e = b.subtract(c);
        System.out.println("成功");
        System.out.println(d);
        System.out.println(e);
        System.out.println(d.equals(e));
    }

    private static void testABS() {
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        float diff = 1e-6f;
        if (Math.abs(a - b) < diff) {
            System.out.println(Math.abs(a - b));
            System.out.println(true);
        }
    }
}
