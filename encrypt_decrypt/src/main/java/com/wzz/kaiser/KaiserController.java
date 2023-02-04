package com.wzz.kaiser;

public class KaiserController {
    //凯撒加密   抽取代码 ctrl + alt +m
    public static void main(String[] args) {
        //定义原文
        String input = "Hello World";
        //把原文向右移动三位
        int key = 3;
        String s = encrypt(input,key);
        System.out.println("加密后===="+s);

        //解密
        String s1 = decrypt(s,key);
        System.out.println("解密后===="+s1);
    }

    /**
     * 解密
     * @param s
     * @param key
     * @return
     */
    public static String decrypt(String s, int key) {
        char[] chars = s.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (char aChar : chars) {
            int b = aChar;
            //偏移数据解密
            b = b - key;
            char newb = (char) b;
            stringBuffer.append(newb);
        }
        return stringBuffer.toString();
    }

    /**
     * 加密
     * @param input
     * @return
     */
    public static String encrypt(String input,int key) {
        //把字符串变成字节数组
        char[] chars = input.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (char aChar : chars) {
            int b = aChar;
            //往右移动三位
            b = b + key;
            char newb = (char) b;
            stringBuffer.append(newb);
        }
        return stringBuffer.toString();
    }
}
