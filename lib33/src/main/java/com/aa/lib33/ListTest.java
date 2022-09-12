package com.aa.lib33;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        ssTestRemove();
    }

    private static void ssTestRemove() {
        List<String> aa = new ArrayList<>();
        aa.add("1");
        aa.add("2");
        aa.add("3");
        aa.add("4");
        Iterator<String> it = aa.iterator();
        while (it.hasNext()) {
            if ("1".equals(it.next())) {
                it.remove();
            }
        }
        System.out.println(aa);
    }

    private static void aaTestArraysAsList() {
        String[] aa = {"aa", "bb"};
        List<String> aa2 = Arrays.asList(aa);
        List<String> aa3 = new ArrayList<>(aa2);
        aa3.add("cc");
        System.out.println(aa3);
    }
}
