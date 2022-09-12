package com.aa.lib33;

public class TestFive {
    public static void main(String[] args) {
        Test obj1 = new Test();
        Test obj2;
        obj1.a = 10;
        obj2 = obj1.cloning();
        obj2.a  = 20;
        System.out.println("obj1.a = "+obj1.a);
        System.out.println("obj2.a = "+ obj2.a);
    }

}
class Test implements Cloneable{
    int a;
    Test cloning(){
        try{
            return (Test) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("CloneNotSupportedException is caught");
            e.printStackTrace();
            return this;
        }
    }

}
