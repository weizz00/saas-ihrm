package com.wzz.bytebit;

public class ByteBitController {
    /**
     * byte 和 bit d的关系
     */
//    public static void main(String[] args) {
//        String a = "a";
//        byte[] bytes = a.getBytes();
//        for (byte aByte : bytes) {
//            int c = aByte;
//            System.out.println(c);
//            //byte 字节 对应的bit是多少
//            String s = Integer.toBinaryString(c);
//            System.out.println("对应的bit===="+s);
//        }
//    }

    /**
     *根据编码的格式不一样，对应的字节也不一样
     * 如果是UTF -8,一个中文对应三个字节
     * 如果是GBK，一个中文对应两个字节
     * @param args
     */
    public static void main(String[] args) {
        String a  = "尚";
        byte[] bytes = a.getBytes();
        for (byte aByte : bytes) {
            System.out.println(aByte);
            String s = Integer.toBinaryString(aByte);
            System.out.println(s);
        }
    }
}
