package com.wzz.signature;

import com.wzz.rsa.RsaController;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class SignatrueController {
    public static void main(String[] args) throws Exception {
        String a = "123";
        //算法
        String algorithm = "RSA";
        //读取私钥
        PrivateKey privateKey = RsaController.getPrivateKey("a.pri", "RSA");
        PublicKey publicKey = RsaController.getpubvateKey("a.pub","RSA");

        //获取数字签名
        String signaturedData = getSignatuer(a,"sha256withrsa",privateKey);
        System.out.println(signaturedData);

        //校验签名
        boolean b = verifSignature(a,"sha256withrsa",publicKey,signaturedData);
        System.out.println(b);
    }

    /**
     * 校验签名
     * @param input      ：原文
     * @param algorithm  ：算法
     * @param publicKey  ：公钥
     * @param signaturedData  ：数字签名
     * @return
     */
    private static boolean verifSignature(String input, String algorithm, PublicKey publicKey, String signaturedData) throws Exception{
        //获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        //初始化校验
        signature.initVerify(publicKey);
        //传入原文
        signature.update(input.getBytes());
        //校验数据
        return signature.verify(Base64.decode(signaturedData));
    }

    /**
     * 生成数字签名
     * @param input          :原文
     * @param algorithm      ：算法
     * @param privateKey     ：私钥
     * @return
     */
    private static String getSignatuer(String input, String algorithm, PrivateKey privateKey) throws Exception{
        //获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        //初始化签名
        signature.initSign(privateKey);
        //传入原文
         signature.update(input.getBytes());
         //开始签名
        byte[] sign = signature.sign();
        //使用Base64转码
        return Base64.encode(sign);
    }
}
