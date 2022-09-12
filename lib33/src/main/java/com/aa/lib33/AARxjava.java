package com.aa.lib33;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AARxjava {
    public static void main(String[] args) {
//        Flowable.just("Hello world").subscribe(System.out.println(it););
        System.out.println("开始");
        Integer[] i = fun1(1, 2, 3, 4, 5, 6);   // 返回泛型数组
        fun2(i);

        String[] tt = fun3("33333","rrrrr","ttttt","rrrrrr","rerrere","sfd fd");
        fun2(tt);
    }

    public static <T> T getObject(Class<T> c) throws InstantiationException, IllegalAccessException {
        return c.newInstance();
    }

    private static void aaTest() {
        List<?>[] list15 = new ArrayList<?>[10]; //OK
        List<Integer>[] aa = new ArrayList[10];
        List<?> list = new ArrayList<>();
    }


    @SafeVarargs
    public static <T> T[] fun1(T... arg) {  // 接收可变参数
        return arg;            // 返回泛型数组
    }

    public static <T> void fun2(T[] param) {   // 输出
        System.out.print("接收泛型数组：");
        for (T t : param) {
            System.out.println(t + "、");
        }
    }


    @SafeVarargs
    public static <E> E[] fun3(E...aaa){
        return aaa;
    }

    public static <T> T[] ArrayWithTypeToken(Class<T> type, int size) {
        T[] array = (T[]) Array.newInstance(type, size);
        return array;
    }

}
