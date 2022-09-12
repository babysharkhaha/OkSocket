package com.aa.lib33;

public class TestIt2 {
    public static void main(String[] args) {
        int[] myArray = {1,2,3,4,5};
        ChangeIt2.doIt(myArray);
        for(int i:myArray)System.out.println(i);
    }
}
class ChangeIt2{
    static void doIt(int[] z){
        int[] A = z;
        A[0] = 99;
    }
}