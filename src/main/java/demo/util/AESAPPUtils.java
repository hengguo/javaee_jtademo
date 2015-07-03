package demo.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



public class AESAPPUtils {

    public static byte[] key = new byte[] { 0x08, 0x08, 0x04, 0x0b, 0x02, 0x0f, 0x0b, 0x0c,0x01, 0x03, 0x09, 0x07, 0x0c, 0x03, 0x07, 0x0a };

    /*
     * 转为十六进制
     */
    private static String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;
        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10)
                strbuf.append("0");
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }

    /*
     * 转为二进制
     */
    private static byte[] asBin(String src) {
        if (src.length() < 1)
            return null;
        byte[] encrypted = new byte[src.length() / 2];
        for (int i = 0; i < src.length() / 2; i++) {
            int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);
            encrypted[i] = (byte) (high * 16 + low);
        }
        return encrypted;
    }

    /*
     * 加密
     */

    public static String encryptAES(String data) {
        SecretKeySpec sKey = new SecretKeySpec(key, "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, sKey);
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return asHex(encrypted);
        } catch (Exception e) {
            return null;
        }
    }
    
    
    /*
     * 解密
     */
    public static String decryptAES(String encData) {
        byte[] tmp = asBin(encData);
        SecretKeySpec sKey = new SecretKeySpec(key, "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, sKey);
            byte[] decrypted = cipher.doFinal(tmp);
            return new String(decrypted);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(decryptAES(encryptAES("1A23")));
    }
    
}
