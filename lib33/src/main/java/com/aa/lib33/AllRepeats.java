package com.aa.lib33;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class AllRepeats {
    public static void main(String[] args) {
        aaTest();
    }

    private static void aaTest() {
        int length = 100000000;
        int[] aa = getNumbers(length);
        Arrays.sort(aa);
        HashSet<Integer> hashSet = new HashSet<Integer>();
        int[] notRepeats = new int[length];
        for (int i = 0; i < aa.length; i++) {
            hashSet.add(aa[i]);
//            if (i == 0) {
//                if (aa[i] != (aa[i + 1])) {
//                    notRepeats[i] = aa[i];
//                }
//            } else if (i == aa.length - 1) {
//                if (aa[i] != aa[i - 1]) {
//                    notRepeats[i] = aa[i];
//                }
//            } else {
//                if (aa[i] != aa[i + 1] && aa[i] != aa[i - 1]) {
//                    notRepeats[i] = aa[i];
//                }
//            }
        }
        long allSum = getSum(aa);
//        long notRepeatSum = getSum(notRepeats);
        long notRepeatSum = getSum(hashSet);
        System.out.println("10万个数字中，所有数字的和   =  " + allSum);
        System.out.println("10万个数字中，所有非重复数字的和   =  " + notRepeatSum);
        System.out.println("10万个数字中，所有重复数字的和   =  " + (allSum - notRepeatSum));
//        System.out.println(hashSet);

    }

    private static long getSum(List<String> aa) {
        long temp = 0;
        for (String a : aa) {
            temp += Long.parseLong(a);
        }
        return temp;
    }

    private static long getSum(HashSet<Integer> aa) {
        long temp = 0;
        Iterator<Integer> a =aa.iterator();
        while (a.hasNext()){
            temp += a.next();
        }
//        for (int b : aa) {
//            temp += b;
//        }
        return temp;
    }

    private static long getSum(int[] aa) {
        long temp = 0;
        for (int a : aa) {
            temp = temp + a;
        }
        return temp;
    }

    private static int[] getNumbers(int length) {
        int[] data = new int[length];
        for (int i = 0; i < data.length; i++) {
            data[i] = ((int) (Math.random() * data.length));
        }
        return data;
    }
}
