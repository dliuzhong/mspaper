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
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	/**
	 * @功能 转换字符串值为int型值
	 * @参数 value为要转换的字符串
	 * @返回值 int型值
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
	 * @功能 转换字符串值为int型值
	 * @参数 value为要转换的字符串
	 * @返回值 int型值
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
	 * @功能 解决通过提交表单产生的中文乱码
	 * @参数 value为要转换的字符串
	 * @返回值 String型值
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
	 * @功能 解决通过提交表单产生的中文乱码
	 * @参数 value为要转换的字符串
	 * @返回值 String型值
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
	 * @功能 解决通过提交表单产生的中文乱码
	 * @参数 value为要转换的字符串
	 * @返回值 String型值
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
     * @功能 将Date型日期转换成指定格式的字符串形式，如“yyyy-MM-dd HH:mm:ss”
     * @参数 date为要被转换的Date型日期
     * @返回值 String型值
     */
	public static String changeTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
		return format.format(date);		
	}
	 /**
     * @功能 将Date型日期转换成指定格式的字符串形式，如“yyyy年MM月dd日”
     * @参数 date为要被转换的Date型日期
     * @返回值 String型值
     */
	public static String changeDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d日");
		return format.format(date);		
	}
	/* 产生随机字符串 */		
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
	// 去掉字符串中的回车和换行
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	// 去掉字符串中的回车和换行
	public static String replaceAllBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s+|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	// 去除html标签
	public static String replaceHtml(String html){ 
        String regEx = "<.+?>|&nbsp;"; //表示标签 
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
	// 把阿拉伯数字转换成中文数字
	public static String toChineseNumber(String number) {
		String[] cn = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十"};
		String cNum = "";
		char n;
		if (number.length() > 0) {
			for (int i = number.length() - 1, j = 0;i >= 0 && j < number.length();i--, j++) {
				n = number.charAt(i);
				switch(n) {
					case '0' : {
						// 检测i = 0，数字只一有一位
						if (i > 0) {
							if (cNum.length() > 0) {
								// 防止有多个"零"
								if (!(cNum.charAt(0) + "").equals("零")) {
									cNum = "零" + cNum;
								}
									
							}
							
						} else {
							cNum = "零" + cNum;
						}
						break;
					}
					case '1' : {
						if (number.length() == 2) {
							cNum = cn[j] + cNum; break;
						} else {
							cNum = "一" + cn[j] + cNum; break;
						}
						
					}
					case '2' : {
						cNum =  "二" + cn[j] + cNum; break;
					}
					case '3' : {
						cNum =  "三" + cn[j] + cNum; break;
					}
					case '4' : {
						cNum =  "四" + cn[j] + cNum; break;
					}
					case '5' : {
						cNum =  "五" + cn[j] + cNum; break;
					}
					case '6' : {
						cNum =  "六" + cn[j] + cNum; break;
					}
					case '7' : {
						cNum =  "七" + cn[j] + cNum; break;
					}
					case '8' : {
						cNum =  "八" + cn[j] + cNum; break;
					}
					case '9' : {
						cNum =  "九" + cn[j] + cNum; break;
					}
					
				}
			}
		}
		return cNum;
		
	}
	
	
	    
	    public static String delHTMLTag(String htmlStr) {
	        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
	        Matcher m_script = p_script.matcher(htmlStr);
	        htmlStr = m_script.replaceAll(""); // 过滤script标签

	        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
	        Matcher m_style = p_style.matcher(htmlStr);
	        htmlStr = m_style.replaceAll(""); // 过滤style标签

	        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
	        Matcher m_html = p_html.matcher(htmlStr);
	        htmlStr = m_html.replaceAll(""); // 过滤html标签

	        return htmlStr.trim(); // 返回文本字符串
	    }
//	public static void main(String[] args) {
//		System.out.println(MyTools.toChineseNumber("567"));
//	}
}

