package com.example.demo;

import com.example.demo.collection.BaseService;
import com.example.demo.collection.Child;
import com.example.demo.collection.Parent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 泛型擦除
     */
    @org.junit.jupiter.api.Test
    public void main1() {

        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("abc");

        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(123);
        System.out.println(list1.getClass());
        System.out.println(list2.getClass());
        System.out.println(list1.getClass() == list2.getClass()); // true
    }

    @org.junit.jupiter.api.Test
    public void test2(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list = list.stream().filter(o -> o.equals(3)).collect(Collectors.toList());
        System.out.println(list.size());
        System.out.println(list != null);
        System.out.println(TestEn.INTRODUCE_FEE.equals("INTRODUCE_FEE"));

        String date = "2019-03-02";
        String [] str = date.split("-");
        Integer year = Integer.valueOf(str[0]);
        Integer month = Integer.valueOf(str[1]);
        Integer day = Integer.valueOf(str[2]);
        int ans = 0;
        int [] days = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        if( (year % 4 ==0 && year % 100 != 0) || year %400==0){
            days[1]++;
        }

        for(int i = 0; i < month - 1; ++i){
            ans += days[i];
        }
        System.out.println( ans+ day);


//        Long longs = null;
//        System.out.println(1==longs);

//        List<String> test = new ArrayList<>();
//        test.get(0);



        Parent parent =new Parent();
        Child child =new Child();
        BaseService<Child> baseService = new BaseService<>();
        baseService.queryByParam(Parent.class,"","");



    }

}
