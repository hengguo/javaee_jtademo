package demo.util;

/**
 * 压缩加密  解密压缩
 */
public class GzipAESUtil {
	
	/**
	 * 先压缩后加密
	 * @param str 要处理的字符串
	 * @return
	 */
	public static String compressThenEncryptAES(String str){
		try {
			return AESAPPUtils.encryptAES(GZipStrUtil.compress(str));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 先解密后解压缩
	 * @param str 要处理的字符串
	 * @return
	 */
	public static String decryptAESThenUnCompress(String str){
		try {
			return GZipStrUtil.unCompress(AESAPPUtils.decryptAES(str));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
