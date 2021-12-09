package com.example.demo;

public class Test {
    public static int a =1;
    public int b =1;
    public Test(){
        a++;
        b++;
    }

    public static void main(String[] args) {
        Test test1 = new Test();
        Test test2 = new Test();
        System.out.println(test1.a +","+test1.b +","+test2.a+","+test2.b);

        System.out.println(1|2);
        System.out.println(1|3);
        System.out.println(1&2);
        System.out.println(1&3);
        int a = 1<<('a'-'a');
        int b = 1<<('c'-'a');
        int c = 1<<('z'-'a');
        System.out.println(a);
        System.out.println(b);
        System.out.println(a&b);
        System.out.println(c);
        System.out.println(Integer.MAX_VALUE);
    }
}
