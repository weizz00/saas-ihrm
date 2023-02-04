package com.wzz.ascii;

public class AsciiController {
    /**
     * Ascii码
     * @param args
     */
    public static void main(String[] args) {
//        char a = 'a';
//        int b = a;
//        //打印b，在Ascii当中十进制的数字对应的是多少
//        System.out.println(b);
        //定义字符串
        String a = "AZa";
        //1、需要拆开字符串
        char[] chars = a.toCharArray();
        for (char aChar : chars) {
            int asciiCode = aChar;
            System.out.println(asciiCode);
        }
    }
}
