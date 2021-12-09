package com.example.demo.util.other;

import java.util.Random;

public class ValidCodeUtil {
    public static String randString(int length,String str){
        StringBuffer stringBuffer= new StringBuffer();
        if("".equals(str)){
            str = "0123456789";
        }
        for (int i = 0; i <length; i++)
        {
            Random r=new Random();
            stringBuffer.append(str.charAt(r.nextInt(str.length())));
        }
        return stringBuffer.toString();
    }
}
