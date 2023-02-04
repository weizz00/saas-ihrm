package com.wzz.desaes;


import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * DES、CES加密解密
 */
public class DesaesController {

    public static void main(String[] args) throws Exception {
         //原文
         String input = "硅谷";
         //定义key  des加密，密钥必须为8字节
        String key= "12345678";
        //算法
        String transformation = "DES";
        //加密类型
        String algorihtm = "DES";
        //加密
        String encryptDes= encryptDes(input, key, transformation, algorihtm);
        System.out.println("加密===="+encryptDes);
        //解密
        String decryptDes= decryptDes(encryptDes, key, transformation, algorihtm);
        System.out.println("解密===="+decryptDes);
    }

    /**
     * 使用DES解密
     * @param encryptDes  密文
     * @param key  密钥
     * @param transformation  加密算法
     * @param algorihtm 加密类型
     * @return
     */
    private static String decryptDes(String encryptDes, String key, String transformation, String algorihtm) throws Exception{
        Cipher cipher = Cipher.getInstance(transformation);
        //加密、解密规则
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),algorihtm);
        //Cipher.DECRYPT_MODE表示解密
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
        byte[] bytes = cipher.doFinal(Base64.decode(encryptDes));

        return new String(bytes);
    }

    /**
     * 使用DES加密
     * @param input
     * @param key
     * @param transformation
     * @param algorihtm
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static String encryptDes(String input, String key, String transformation, String algorihtm) throws Exception {
        //创建加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        //创建加密规则  第一个参数表示加密的key的字节
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorihtm);
        //加密初始化 第一个参数表示加密模式  第二个参数表示加密规则
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
        //调用加密方法   参数表示原文的字节数组
        byte[] bytes = cipher.doFinal(input.getBytes());
//        //打印密文
//        System.out.println(new String(bytes));

        //创建Base64对象
        String encode = Base64.encode(bytes);
        System.out.println(encode);
        return encode;
    }
}
