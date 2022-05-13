package zjw.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * <p> AES CBC模式例子。</p>
 *
 */
public class AESCBCUtils {
    private static final int KEY_LENGTH_16 = 16;
    private static final int KEY_LENGTH_32 = 32;
    private static final String AES = "AES";
    private static final String AES_CBC_NO_PADDING = "AES/CBC/NoPadding";
    //加密key长度需要为16位或32位
    public static final String DEFAULT_KEY = "1111222233334444";
    //偏移矢量，必须为16位
    public static final String DEFAULT_IV = "5555666677778888";

    public static void main(String[] args) throws Exception {

        //待加密内容
        String content = "admin";
        System.out.println("加密前的内容：" + content);

        String encrypted = AESCBCUtils.encryptByCBC(DEFAULT_KEY, content, DEFAULT_IV);
        System.out.println("加密后的内容：" + encrypted);

        String decrypt = AESCBCUtils.decryptByCBC(DEFAULT_KEY, encrypted, DEFAULT_IV);
        System.out.println("解密后的内容：" + decrypt);
    }

    /**
     * AES ECB模式加密
     *
     * @param key     加密的秘钥
     * @param content 待加密内容
     * @param iv      偏移矢量
     * @return 加密后的内容
     * @throws Exception 异常信息
     */
    public static String encryptByCBC(String key, String content, String iv) throws Exception {
        checkKey(key);
        checkIV(iv);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), AES);
        //AES/ECB/PKCS5Padding 格式为 "算法/模式/补码方式"
        Cipher cipher = Cipher.getInstance(AES_CBC_NO_PADDING);
        //加密内容长度必须要为blockSize的整数倍
        int blockSize = cipher.getBlockSize();
        int contentLength = content.getBytes().length;
        if (contentLength % blockSize != 0) {
            contentLength = contentLength + (blockSize - (contentLength % blockSize));
        }
        byte[] newBytes = new byte[contentLength];
        System.arraycopy(content.getBytes(), 0, newBytes, 0, content.getBytes().length);
        //偏移矢量
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        //设置加密模式，加密的key，偏移矢量
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        //加密
        byte[] bytes = cipher.doFinal(newBytes);
        //base64编码进行二次加密
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * 检查key是否合法
     *
     * @param key {@link String}秘钥信息
     * @throws Exception 异常信息
     */
    private static void checkKey(String key) throws Exception {
        if (key == null || key.length() != KEY_LENGTH_16 && key.length() != KEY_LENGTH_32) {
            throw new Exception("加密秘钥不正确");
        }
    }

    /**
     * 检查偏移矢量是否合法
     *
     * @param iv {@link String} 偏移矢量
     * @throws Exception 异常信息
     */
    private static void checkIV(String iv) throws Exception {
        if (iv == null || iv.length() != KEY_LENGTH_16) {
            throw new Exception("偏移矢量不正确，必须为16位");
        }
    }

    /**
     * AES ECB模式解密
     *
     * @param key     加密的秘钥
     * @param encrypt 加密后的内容
     * @param iv      偏移矢量
     * @return 解密后的内容
     * @throws Exception 异常信息
     */
    public static String decryptByCBC(String key, String encrypt, String iv) throws Exception {
        checkKey(key);
        checkIV(iv);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), AES);
        //AES/ECB/PKCS5Padding 格式为 "算法/模式/补码方式"
        Cipher cipher = Cipher.getInstance(AES_CBC_NO_PADDING);
        //偏移矢量
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        //设置为解密模式，解密的key,偏移矢量
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        //base64解密
        byte[] decodeBuffer = new BASE64Decoder().decodeBuffer(encrypt);
        //aes解密
        byte[] bytes = cipher.doFinal(decodeBuffer);
        return new String(bytes);
    }

    /**
     * 生成密钥（盐）
     * @param n 密钥长度
     * @return
     */
    public static String getCKey(int n) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
            if ("char".equalsIgnoreCase(str)) { // 产生字母
                int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (nextInt + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(str)) { // 产生数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

}
