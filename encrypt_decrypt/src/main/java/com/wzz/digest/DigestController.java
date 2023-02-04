package com.wzz.digest;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestController {
    /**
     * 消息摘要算法
     * @param args
     */
    public static void main(String[] args) throws Exception{
        //定义原文
        String input = "aa";
        //算法
        String algorithm = "MD5";
        //MD5
        String md5 = getDigest(input, algorithm);
        System.out.println("md5===="+md5);

        //SHA-1
        String sha1 = getDigest(input, "SHA-1");
        System.out.println("SHA-1"+sha1);

        //SHA-256
        String sha256 = getDigest(input, "SHA-256");
        System.out.println("SHA-256"+sha256);

        //SHA-512
        String sha512 = getDigest(input, "SHA-512");
        System.out.println("SHA-512"+sha512);
    }

    /**
     *
     * @param input   原文
     * @param algorithm 算法
     * @return
     * @throws Exception
     */
    private static String getDigest(String input, String algorithm) throws Exception {
        //创建消息摘要对象
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        //执行消息摘要算法
        byte[] bytes = digest.digest(input.getBytes());
        return toHex(bytes);
    }

    private static String toHex(byte[] bytes) {
        //迭代秘闻
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            String s = Integer.toHexString(aByte & 0xff);
            //如果是长度为1，需要在高位补0
            if (s.length() == 1){
                s = "0"+s;
            }
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
//        System.out.println(stringBuilder);
        //使用Base64进行转码
//        System.out.println(Base64.encode(bytes));
    }
}
