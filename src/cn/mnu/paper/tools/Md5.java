package cn.mnu.paper.tools;

import java.security.MessageDigest;

/**
 * ��������м��ܺ���֤
 * @author MDL
 *
 */
public class Md5 {
	// ʮ������������ ���ַ���ӳ������
	private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9",
		"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q",
		"r","s","t","u","v","w","x","y","z"};
	/**
	 * ���ַ�������MD5����
	 * @param Ҫ���ܵ��ַ���
	 * @return ���ܺ���ַ���
	 */
	private static String encodeByMD5(String encodeString) {
		if (encodeString != null) {
			try {
				// ��������ָ���㷨���Ƶ���ϢժҪ
				MessageDigest md = MessageDigest.getInstance("MD5");
				// ʹ��ָ�����ֽ������ժҪ���������£�Ȼ�����ժҪ����
				byte[] results = md.digest(encodeString.getBytes());
				// ���õ����ֽ��������ַ�������
				String resultString = byteArrayToHexString(results);
				return resultString.toUpperCase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * ת���ֽ�����Ϊʮ�������ַ���
	 * @param �ֽ�����
	 * @return ʮ�������ַ���
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0;i < b.length; i++) {
			sb.append(byteToHexString(b[i]));
		}
		return sb.toString();
	}
	/**
	 * ��һ���ֽ�ת����ʮ�����Ƶ��ַ���
	 * @param �����ֽ�
	 * @return ʮ�������ַ���
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
	 * ��inputString ����
	 * @param inputString
	 * @return ���ܺ�
	 */
	
	public static String generatePassword(String inputString) {
		return encodeByMD5(inputString);
	}
	/**
	 * ��֤����������Ƿ���ȷ
	 * @param ���ܵ�����
	 * @param Ҫ��֤������
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
		System.out.println("ԭ���룺" + pw);
		epw = Md5.generatePassword(pw);
		System.out.println("���ܺ�" + epw);
		System.out.println(Md5.validatePassword(epw, pw));
		
		
	}
}
