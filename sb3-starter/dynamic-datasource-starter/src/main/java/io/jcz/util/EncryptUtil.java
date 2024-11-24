package io.jcz.util;


import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EncryptUtil {

    public static final String SIGN_TYPE_RSA = "RSA";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 245;
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 256;


    /**
     * 生成公私钥
     */
    public static Map<String, String> generateRSAKeyPairs() throws NoSuchAlgorithmException {
        Map<String, String> map = new HashMap<>();
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.genKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        Base64.Encoder encoder = Base64.getEncoder();
        map.put("publicKey", new String(encoder.encode(publicKey.getEncoded())));
        map.put("privateKey", new String(encoder.encode(privateKey.getEncoded())));
        return map;
    }

    /**
     * 加密
     */
    public static String rsaEncrypt(String content, String publicKey, String charset) {
        ByteArrayOutputStream out = null;
        try {
            PublicKey pubKey = getPublicKeyFromX509(SIGN_TYPE_RSA, new ByteArrayInputStream(publicKey.getBytes()));
            Cipher cipher = Cipher.getInstance(SIGN_TYPE_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] data = !StringUtils.hasText(charset) ? content.getBytes() : content.getBytes(charset);
            int inputLen = data.length;
            out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = encodeBase64(out.toByteArray());

            return !StringUtils.hasText(charset) ? new String(encryptedData) : new String(encryptedData, charset);
        } catch (Exception e) {
            throw new RuntimeException("EncryptContent = " + content + ",charset = " + charset, e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("EncryptContent = " + content + ",charset = " + charset, e);
            }
        }
    }

    /**
     * 解密
     */
    public static String rsaDecrypt(String content, String privateKey, String charset) {

        ByteArrayOutputStream out = null;
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA, new ByteArrayInputStream(privateKey.getBytes()));
            Cipher cipher = Cipher.getInstance(SIGN_TYPE_RSA);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] encryptedData = !StringUtils.hasText(charset) ? decodeBase64(content.getBytes())
                    : decodeBase64(content.getBytes(charset));
            int inputLen = encryptedData.length;
            out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            byte[] decryptedData = out.toByteArray();

            return !StringUtils.hasText(charset) ? new String(decryptedData) : new String(decryptedData, charset);
        } catch (Exception e) {
            throw new RuntimeException("EncodeContent = " + content + ",charset = " + charset, e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("EncryptContent = " + content + ",charset = " + charset, e);
            }
        }
    }

    public static byte[] encodeBase64(final byte[] binaryData) {
        return Base64.getEncoder().encode(binaryData);
    }

    public static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        copyLarge(ins, baos);

        byte[] encodedKey = decodeBase64(baos.toByteArray());

        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        copyLarge(ins, baos);
        byte[] encodedKey = baos.toByteArray();

        encodedKey = decodeBase64(encodedKey);

        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    public static byte[] decodeBase64(final byte[] base64Data) {
        return Base64.getDecoder().decode(base64Data);
    }

    public static void copyLarge(final InputStream inputStream, final OutputStream outputStream)
            throws IOException {
        Objects.requireNonNull(inputStream, "inputStream");
        Objects.requireNonNull(outputStream, "outputStream");
        final byte[] buffer = new byte[8192];
        int n;
        while (-1 != (n = inputStream.read(buffer))) {
            outputStream.write(buffer, 0, n);
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //Map<String, String> map = generateRSAKeyPairs();
        //System.out.println("publicKey: " + map.get("publicKey"));
        //System.out.println("privateKey: " + map.get("privateKey"));
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt9C8pitwKmFt68tKADiSV03QrtS/YxSkV/ulNZk1D9h++HtKa8iHVoBOd7MOulJUNLDzfKDkOUfULFyeWWbOmoV/b4aDomJ6pbuGpsxTNS4QXcx3omF4kyTyIqFJVJ67dcg8ZTkDl5Hro9UwQgxmwoc3nhicCx8Ot+DrdZ+vkhf4iIsKSPZ+GGUCX3/GgLXSuLtin4mctCF9jTqgqJOrPhhYtrenPeur64HZIBn/GmauHvaJWhtmBF+7FYPq406ADXoUGb0HtZ/TqQVfd+spSpqvANbgALU3p+4uegER2v26cvBj+rZT6xC34FGM6ewVJa+0wuqhRhcmS8zo3PQm4wIDAQAB";
        String cipherText = rsaEncrypt("io@jcz", publicKey, null);

        System.out.println("密文: " + cipherText);
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC30LymK3AqYW3ry0oAOJJXTdCu1L9jFKRX+6U1mTUP2H74e0pryIdWgE53sw66UlQ0sPN8oOQ5R9QsXJ5ZZs6ahX9vhoOiYnqlu4amzFM1LhBdzHeiYXiTJPIioUlUnrt1yDxlOQOXkeuj1TBCDGbChzeeGJwLHw634Ot1n6+SF/iIiwpI9n4YZQJff8aAtdK4u2KfiZy0IX2NOqCok6s+GFi2t6c966vrgdkgGf8aZq4e9olaG2YEX7sVg+rjToANehQZvQe1n9OpBV936ylKmq8A1uAAtTen7i56ARHa/bpy8GP6tlPrELfgUYzp7BUlr7TC6qFGFyZLzOjc9CbjAgMBAAECggEAVXn0F8OQU6zdzC3K2iJ0brOpTHtzCjSBncZP04ZMhfNTS+ItP8TOpntgwcnamesJ7Dyy5Sv+JOLRd9WT7xffEDzu/yIKmVr5FJOI8IyITuz+QFgB89kmlCo0jMT6kCaGHqW/7AQgtv1smdFbsQMKUABH7TUdbxCArCIj7B0XLDQ+K0PIvbxcL1ruDZhvAnLp1yuODxT9/Mlq4kZlJcbd1tPW+EW+942ry1Hrt6uVp8BXqk/eWrfI99awU4iqVDBoWRjU8nTQlDOAqjQfUwihlxGl/P3zjJozDPLtrT3ClxnSmzU74ztxxuZhiY+oX0AUgQFUjQHY58LjeFllQrRkyQKBgQDoef2B45T+h+2p835RWYktEFMGwp7b8A6xTBQQ3QYuWaNXlnBMFwWVzDcxEhBqd1obFTMa/UmKwwFzSlckEkd9QD7H5h0xIH1RifjsLcNMojFEtkjciNQelP3QYA2pPWjNt668Jt+KDUkaBYY8tdqero9o5ANdC/5GSq2pEVg5mwKBgQDKaj3IBeluS8GAx8g2scP9q43tQJbK4bBowb8uY6eh6iR5Ncb3LcPIBHBI6MjEJlcEfneK2eFv8Aty8HnctFWlEoPP2vzQv50m9e+JYpNP2zwxOkEyme57fDOvhvXq4woY5Sy9ATFsHPTNQNNRuX9LCR9zoVOfDZcqbZYhyOpgWQKBgFi6PpQ1fSdMT9wej+aSirWFVnrtCt6cl675f2sUQIiuM5Z5V3DjFIGgyKvU3ikUVnKhu7igVVI9rS8If4vH4jseqxd2b0vRv6LASx4xNSnpeW5lOBfbrUHi0aCXGH+wbXCem3U6TPUOrwjcdOh06/TGYPvCkroPmsPPOsqdURPjAoGBAKBNb0w/0lR1TnPsxFT8h5kYCjiA1GssPYdZGU1lwtZqFiSgQEh1R/3Z9grnwEgHjRaUCZw/XVhHJOoMJJZzgvhOJ3GvnlS7y3uVffSxyrSNjaf22Ld4TPQistQp8Avrbo20wa+ViEx0yjLbSgAJBhSozu3GL/Fg0/zX+PQcp/FRAoGADWoHNL3x8m+H/KzcG4yOUflQaJmyd21suFyqPUrCYpXU/6qerzADGN+RiQhNbTjccnRTaVTi42XK8J0Owt0pb4OCSzicJy0FDQU/nzwKxdDERjrYmJ8to3ED9dG89h+6qUz3ss0VpuZomkaW9Z+LjP1AL442BZZSmPTZRbV51IQ=";
        String plainText = rsaDecrypt(cipherText, privateKey, null);
        System.out.println("解密之后: " + plainText);
    }
}
