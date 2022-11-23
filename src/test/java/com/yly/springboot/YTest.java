package com.yly.springboot;

import org.junit.jupiter.api.Test;

public class YTest {

    public static void main(String[] args) {
//        reverse((int)Math.pow(2,64));
        System.out.println(0x7fffffff);
        System.out.println(Math.pow(2,31)-1);
    }

    @Test
    public static int reverse(int x) {
        int result;
        if(x > -10 && x < 10){
            return x;
        }else{
            String s = Integer.toString(x);
            char [] c = new char[s.length()];
            for (int i = 0; i < s.length(); i++) {
                c[i] = s.charAt(s.length()-1-i);
            }
            String s1 = String.valueOf(c);
            result = Integer.decode(s1);
        }
        return result;
    }
}
