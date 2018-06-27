package com.hycun.session.boot.demo;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class TestMain {

    public String signByAes(String content,String signKey){
        try{
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            kgen.init(128, new SecureRandom(signKey.getBytes()));// 利用用户密码作为随机数初始化出
            SecureRandom secureRandom=SecureRandom.getInstance("");
            secureRandom.setSeed(signKey.getBytes());

            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回

            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

            BASE64Encoder encoder = new BASE64Encoder();    //需要加上

            return new String(encoder.encode(cipher.doFinal(byteContent)));// 加密
        }catch (Exception e) {
            return "";
        }
    }

    public static void main(String[] args) {
        TestMain testMain=new TestMain();

        System.out.println(testMain.signByAes("ssssAAAAAAAAAA","ssssssssssssssss"));

        System.out.println(testMain.signByAes("ssssWWWWWWW","ssssssssssssssss"));
    }
}
