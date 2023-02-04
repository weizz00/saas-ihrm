package com.wzz.rsa;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
public class RsaController {
    /**
     * 生成公钥私钥
     * @param args
     */
    public static void main(String[] args) throws  Exception{
        //定义原文
        String input = "硅谷";
        //算法
        String algorithm = "RSA";
        //读取私钥
        PrivateKey privateKey = getPrivateKey("a.pri", algorithm);
        PublicKey publicKey = getpubvateKey("a.pub",algorithm);
        System.out.println(privateKey);
        System.out.println(publicKey);

//        //保存公钥、私钥
//        generateKeyToFile(algorithm,"a.pub","a.pri");
        //创建密钥对
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
//        // 生成密钥对
//        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//        // 生成私钥
//        PrivateKey privateKey = keyPair.getPrivate();
//        // 生成公钥
//        PublicKey publicKey = keyPair.getPublic();
//        // 获取私钥的字节数组
//        byte[] privateKeyEncoded = privateKey.getEncoded();
//        // 获取公钥字节数组
//        byte[] publicKeyEncoded = publicKey.getEncoded();
//        //        //获取公钥、私钥的字节数组
//        String privateKeyEncoded1 = Base64.encode(privateKey.getEncoded());
//        String  publicKeyEncoded1= Base64.encode(publicKey.getEncoded());
        //保存到根目录
//        FileUtils.writeStringToFile(new File("a.pub"),publicKeyEncoded1, Charset.forName("UTF-8"));
//        FileUtils.writeStringToFile(new File("a.pri"),privateKeyEncoded1, Charset.forName("UTF-8"));

//        //加密
//        String s = encryptRSA(algorithm, privateKey, input);
//        System.out.println(s);
//        //解密
//        String s1 = decryptRSA(algorithm, publicKey, s);
//        System.out.println(s1);
    }
    /**
     * 读取公钥
     * @param pubPath  ：私钥URL
     * @param algorithm ：算法
     * @return
     */
    public static PublicKey getpubvateKey(String pubPath, String algorithm) throws Exception{
        String pubKeyString = FileUtils.readFileToString(new File(pubPath), Charset.defaultCharset());
        //创建key工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        //创建私钥key的规则
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(pubKeyString.getBytes()));
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 读取私钥
     * @param priPath  ：私钥URL
     * @param algorithm ：算法
     * @return  返回私钥对象
     */
    public static PrivateKey getPrivateKey(String priPath, String algorithm) throws Exception{
        String priKeyString = FileUtils.readFileToString(new File(priPath), Charset.defaultCharset());
        //创建key工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        //创建私钥key的规则
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(priKeyString.getBytes()));
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 解密数据
     *
     * @param algorithm      : 算法
     * @param encrypted      : 密文
     * @param publicKey            : 密钥
     * @return : 原文
     * @throws Exception
     */
    public static String decryptRSA(String algorithm,Key publicKey,String encrypted) throws Exception{
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 私钥解密
        cipher.init(Cipher.DECRYPT_MODE,publicKey);
        // 使用base64进行转码
        byte[] decode = Base64.decode(encrypted);


        // 使用私钥进行解密
        byte[] bytes1 = cipher.doFinal(decode);
        return new String(bytes1);
    }


    /**
     * 使用密钥加密数据
     *
     * @param algorithm      : 算法
     * @param input          : 原文
     * @param privateKey            : 密钥
     * @return : 密文
     * @throws Exception
     */
    public static String encryptRSA(String algorithm, Key privateKey, String input) throws Exception{
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 对加密进行初始化
        // 第一个参数：加密的模式
        // 第二个参数：你想使用公钥加密还是私钥加密
        // 我想使用私钥进行加密
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        // 使用私钥进行加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        return Base64.encode(bytes);
    }

    /**
     *  把公钥私钥保存到目录
     * @param algorithm
     * @param pubPath  表示公钥URL
     * @param priPath  表示私钥URL
     */
    private static void generateKeyToFile(String algorithm, String pubPath, String priPath) throws Exception{
        //创建密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //私钥
        PrivateKey privateKey = keyPair.getPrivate();
        //公钥
        PublicKey publicKey = keyPair.getPublic();
        //获取公钥、私钥的字节数组
        String privateKeyEncoded = Base64.encode(privateKey.getEncoded());
        String publicKeyEncoded = Base64.encode(publicKey.getEncoded());

        //保存到根目录
        FileUtils.writeStringToFile(new File(pubPath),publicKeyEncoded, Charset.forName("UTF-8"));
        FileUtils.writeStringToFile(new File(priPath),privateKeyEncoded, Charset.forName("UTF-8"));

    }
}
