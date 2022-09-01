package com.aa.lib33;

import java.util.Arrays;

public class JiaBan {
    private static String s = "2022-08-31    20:00-22:05  2.0小时\n" +
            "2022-08-30    20:00-21:03  1.0小时\n" +
            "2022-08-29    20:00-22:33  2.5小时\n" +
            "2022-08-27    20:00-24:02  4小时\n" +
            "2022-08-26    20:00-23:09  3小时\n" +
            "2022-08-25    20:00-22:33  2.5小时\n" +
            "2022-08-24    20:00-23:08  3.0小时\n" +
            "2022-08-23    20:00-23:03  3小时\n" +
            "2022-08-22    20:00-23:05  3小时\n" +
            "2022-08-19    20:00-21:34  1.5小时\n" +
            "2022-08-18    20:00-23:03  3.5小时\n" +
            "2022-08-17    20:00-21:39  1.5小时\n" +
            "2022-08-16    20:00-22:44  2.5小时\n" +
            "2022-08-14    14:22-15:23  1小时\n" +
            "2022-08-13    20:00-21:13  1小时\n" +
            "2022-08-12    20:00-21:34  1.5小时\n" +
            "2022-08-11    20:00-21:39  1.5小时\n" +
            "2022-08-10    20:00-22:16  2小时\n" +
            "2022-08-09    20:00-21:36  1.5小时\n" +
            "2022-08-08    20:00-22:18  2小时\n" +
            "2022-08-06    20:00-23:09  2小时\n" +
            "2022-08-05    20:00-23:16  3小时\n" +
            "2022-08-04    20:00-23:42  3.5小时\n" +
            "2022-08-03    20:00-22:17  2小时";

    public static void main(String[] args) {
        System.out.println("haha");
        System.out.println(s.substring(s.indexOf("小时") - 3, s.indexOf("小时")));
        System.out.println(Arrays.toString(s.split("\n")));
        double jiaban = 0 ;
        for(String num : s.split("\n")){
            num = num.substring(s.indexOf("小时") - 3, s.indexOf("小时"));
            if(num.contains("小时")){
                num = num.substring(0,num.indexOf("小时"));
            }
            System.out.println(num);
            jiaban += Double.parseDouble(num);
        }
      System.out.println(String.format("这个月总共加班%s小时",jiaban));
    }

}