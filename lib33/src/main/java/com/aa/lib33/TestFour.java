package com.aa.lib33;

public class TestFour {
    public static void main(String[] args) {
        Integer i = Integer.valueOf(10);
        Integer j = Integer.valueOf(20);
        swap(i,j);
        System.out.println("i="+ i +",  j="+j);
    }
   public static void swap(Integer i,Integer j){
        Integer temp = Integer.valueOf(i);
        i = j;
        j = temp;
   }
}
