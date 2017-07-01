package cn.mnu.paper.tools;


/**
 *
 * @author MDL
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


public class MyTools {
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // ����script��������ʽ
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // ����style��������ʽ
	private static final String regEx_html = "<[^>]+>"; // ����HTML��ǩ��������ʽ
	/**
	 * @���� ת���ַ���ֵΪint��ֵ
	 * @���� valueΪҪת�����ַ���
	 * @����ֵ int��ֵ
	 */
	public static String changeHTML(String value){
		value=value.replace("&","&amp;");
		value=value.replace(" ","&nbsp;");
		value=value.replace("<","&lt;");
		value=value.replace(">","&gt;");
		value=value.replace("\r\n","<br>");
		return value;
	}
	/**
	 * @���� ת���ַ���ֵΪint��ֵ
	 * @���� valueΪҪת�����ַ���
	 * @����ֵ int��ֵ
	 */
	public static int strToint(String value){
		int i=-1;
		if(value==null||value.equals(""))
			return i;
		try{
			i=Integer.parseInt(value);
		}catch(NumberFormatException e){
			i=-1;
			e.printStackTrace();
		}
		return i;
	}
	/**
	 * @���� ���ͨ���ύ����������������
	 * @���� valueΪҪת�����ַ���
	 * @����ֵ String��ֵ
	 */
    public  static String  toChinese(String value) {
    	if (value == null)
    		return "";
    	try {
            value = new String(value.getBytes("ISO-8859-1"), "utf-8");
            return value;
        } catch (Exception e) {
            return "";
        }
    }
   
    /**
	 * @���� ���ͨ���ύ����������������
	 * @���� valueΪҪת�����ַ���
	 * @����ֵ String��ֵ
	 */
    public  static String gb2312toUTF8(String value) {
    	if (value == null)
    		return "";
    	try {
    		System.out.print(value);
            value = new String(value.getBytes("GB2312"), "UTF-8");
            return value;
        } catch (Exception e) {
            return "";
        }
    }
    /**
	 * @���� ���ͨ���ύ����������������
	 * @���� valueΪҪת�����ַ���
	 * @����ֵ String��ֵ
	 */
    public  static String  gbktoUTF8(String value) {
    	if (value == null)
    		return "";
    	try {
    		System.out.print(value);
            value = new String(value.getBytes("GBK"), "utf-8");
            return value;
        } catch (Exception e) {
            return "";
        }
    }
    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    } 
    /**
     * @���� ��Date������ת����ָ����ʽ���ַ�����ʽ���硰yyyy-MM-dd HH:mm:ss��
     * @���� dateΪҪ��ת����Date������
     * @����ֵ String��ֵ
     */
	public static String changeTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
		return format.format(date);		
	}
	 /**
     * @���� ��Date������ת����ָ����ʽ���ַ�����ʽ���硰yyyy��MM��dd�ա�
     * @���� dateΪҪ��ת����Date������
     * @����ֵ String��ֵ
     */
	public static String changeDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy��M��d��");
		return format.format(date);		
	}
	/* ��������ַ��� */		
	private static Random randGen = null;
	private static char[] numbersAndLetters = null;	
	public static final String randomString(int length) {
		if (length < 1) {
			return null;		         
		}
		if (randGen == null) {
			randGen = new Random();
			numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" +
				"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
			//numbersAndLetters = ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();		                 
		}
		char [] randBuffer = new char[length];
		for (int i=0; i<randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];	
			//randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];		         
		}
		return new String(randBuffer);
	}
	// ȥ���ַ����еĻس��ͻ���
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	// ȥ���ַ����еĻس��ͻ���
	public static String replaceAllBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s+|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	// ȥ��html��ǩ
	public static String replaceHtml(String html){ 
        String regEx = "<.+?>|&nbsp;"; //��ʾ��ǩ 
        Pattern p = Pattern.compile(regEx); 
        Matcher m = p.matcher(html); 
        String s = m.replaceAll(""); 
        return s; 
    } 
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	// �Ѱ���������ת������������
	public static String toChineseNumber(String number) {
		String[] cn = {"", "ʮ", "��", "ǧ", "��", "ʮ", "��", "ǧ", "��", "ʮ"};
		String cNum = "";
		char n;
		if (number.length() > 0) {
			for (int i = number.length() - 1, j = 0;i >= 0 && j < number.length();i--, j++) {
				n = number.charAt(i);
				switch(n) {
					case '0' : {
						// ���i = 0������ֻһ��һλ
						if (i > 0) {
							if (cNum.length() > 0) {
								// ��ֹ�ж��"��"
								if (!(cNum.charAt(0) + "").equals("��")) {
									cNum = "��" + cNum;
								}
									
							}
							
						} else {
							cNum = "��" + cNum;
						}
						break;
					}
					case '1' : {
						if (number.length() == 2) {
							cNum = cn[j] + cNum; break;
						} else {
							cNum = "һ" + cn[j] + cNum; break;
						}
						
					}
					case '2' : {
						cNum =  "��" + cn[j] + cNum; break;
					}
					case '3' : {
						cNum =  "��" + cn[j] + cNum; break;
					}
					case '4' : {
						cNum =  "��" + cn[j] + cNum; break;
					}
					case '5' : {
						cNum =  "��" + cn[j] + cNum; break;
					}
					case '6' : {
						cNum =  "��" + cn[j] + cNum; break;
					}
					case '7' : {
						cNum =  "��" + cn[j] + cNum; break;
					}
					case '8' : {
						cNum =  "��" + cn[j] + cNum; break;
					}
					case '9' : {
						cNum =  "��" + cn[j] + cNum; break;
					}
					
				}
			}
		}
		return cNum;
		
	}
	
	
	    
	    public static String delHTMLTag(String htmlStr) {
	        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
	        Matcher m_script = p_script.matcher(htmlStr);
	        htmlStr = m_script.replaceAll(""); // ����script��ǩ

	        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
	        Matcher m_style = p_style.matcher(htmlStr);
	        htmlStr = m_style.replaceAll(""); // ����style��ǩ

	        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
	        Matcher m_html = p_html.matcher(htmlStr);
	        htmlStr = m_html.replaceAll(""); // ����html��ǩ

	        return htmlStr.trim(); // �����ı��ַ���
	    }
//	public static void main(String[] args) {
//		System.out.println(MyTools.toChineseNumber("567"));
//	}
}

