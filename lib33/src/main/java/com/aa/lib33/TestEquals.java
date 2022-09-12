package com.aa.lib33;

public class TestEquals {


    public static void main(String[] args) {
//        String s1 = "runoob";
//        String s2 = "runoob";
//        System.out.println(s1 == s2);

//        int x=4;
//        System.out.println(++x);

//        int x=3;
//        int y=1;
//        if(x==y)
//            System.out.println("不相等");
//        else
//            System.out.println("相等");

//        char c = 65;
//        System.out.println("c = "+c);

//        for(int i=0;i<9;++i){
//            System.out.println(i);
//        }
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Double.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);

        Person.shaRen();
//        Person.shaRen();

        int num = (int) 6.5;
    }

      static class Person {
        private static void shaRen() {
            System.out.println("杀人");
        }
    }
}
