package com.aa.lib33;

public class TestIt {
    public static void main(String[] args) {
        int[] myArray = {1,2,3,4,5};
        ChangeIt.doIt(myArray);
        for(int i: myArray)System.out.println(i);
    }
}
class ChangeIt {
    static void doIt(int[] z){
        z=null;
    }
}


