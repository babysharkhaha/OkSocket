package com.aa.lib33;

import java.util.HashMap;

public class TestSix {
    static class AA {
        private void f(String s) {
            System.out.println(s);
        }

        private void f(Integer s) {
            System.out.println(s);
        }
    }

    static class BB {
        private void f(double s) {
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " double  " + s);
        }

        private void f(int s) {
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "  Integer " + s);
        }
    }

    public static void main(String[] args) {

//        float a = 0.125f;
//        double b = 0.125d;
//        System.out.println((a - b) == 0.0);
//
//        double aaa = 0.9d;
//        double c = 0.8d;
//        double d = 0.7d;
//        double e = 0.6d;
//        double f = 0.5d;
//        double g = 0.4d;
//        double h = 0.3d;
//        System.out.println(aaa);
//        System.out.println(c);
//        System.out.println(d);
//        System.out.println(e);
//        System.out.println(f);
//        System.out.println(g);
//        System.out.println(h);
//        System.out.println("----------------------------------------");
//        System.out.println(aaa - c);
//        System.out.println(c-d);
//        System.out.println(d-e);
//        System.out.println(e-f);
//        System.out.println(f-g);
//        System.out.println(g-h);
//        System.out.println(c-d == d-e);
//        System.out.println(d-e == e-f);
//        System.out.println(1.0 / 0);
        AA aa = new AA();
        aa.f(1);
        BB bb = new BB();
        bb.f((int)22d);

        String cd = "null";
        switch (cd){
            case "":
                System.out.println("空");
                break;
            default:
                System.out.println("default");
                break;
        }
        HashMap<String,Object> hashMap = new HashMap<String,Object>(10000);
        for(int i=0;i<10000;i++){
            hashMap.put(String.valueOf(i),i);
        }
        System.out.println("hashMap.size = " + hashMap.size());
    }
}
