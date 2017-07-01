package cn.mnu.paper.tools;

import java.security.MessageDigest;

/**
 * 对密码进行加密和验证
 * @author MDL
 *
 */
public class Md5 {
	// 十六进制下数字 到字符的映射数组
	private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9",
		"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q",
		"r","s","t","u","v","w","x","y","z"};
	/**
	 * 对字符串进行MD5加密
	 * @param 要加密的字符串
	 * @return 加密后的字符串
	 */
	private static String encodeByMD5(String encodeString) {
		if (encodeString != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(encodeString.getBytes());
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHexString(results);
				return resultString.toUpperCase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 转换字节数组为十六进制字符串
	 * @param 字节数组
	 * @return 十六进制字符串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0;i < b.length; i++) {
			sb.append(byteToHexString(b[i]));
		}
		return sb.toString();
	}
	/**
	 * 将一个字节转化成十六进制的字符串
	 * @param 单个字节
	 * @return 十六进制字符串
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 36;
		int d2 = n % 36;
		return hexDigits[d1] + hexDigits[d2];
	}
	/**
	 * 把inputString 加密
	 * @param inputString
	 * @return 加密后
	 */
	
	public static String generatePassword(String inputString) {
		return encodeByMD5(inputString);
	}
	/**
	 * 验证输入的密码是否正确
	 * @param 加密的密码
	 * @param 要验证的密码
	 * @return
	 */
	public static boolean validatePassword(String password, String inputString) {
		if (password.equals(encodeByMD5(inputString)))
			return true;
		else
			return false;
	}
	public static void main(String[] args) {
		String pw = "1";
		String epw = "";
		System.out.println("原密码：" + pw);
		epw = Md5.generatePassword(pw);
		System.out.println("加密后：" + epw);
		System.out.println(Md5.validatePassword(epw, pw));
		
		
	}
}
