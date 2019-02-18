package com.finance.common.utils;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author steven
 * @version 2005-2-2 modified by chengyl at 20060913 增加sql字符串的单引号转换方法
 */
public class StringUtil {
	public static final String CHARSET_UTF8 = "UTF-8";

	public static String valueOf(Object src) {
		String res = null;
		if (src instanceof byte[]) {
			res = new String((byte[]) src);
		} else {
			res = String.valueOf(src);
		}
		return res;
	}

	/**
	 * 将一个数组中的内容连接成一个字符串，各个数组中的元素使用参数delim分隔
	 * 
	 * @param arr
	 * @param delim
	 * @return
	 */
	public static String arrayToString(String[] arr, String delim) {
		if (arr == null)
			return "null";
		else {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < arr.length; i++) {
				if (i > 0)
					sb.append(delim);
				sb.append(arr[i]);
			}
			return sb.toString();
		}
	}

	/**
	 * 本地字符集转换成unicode
	 * 
	 * @param s
	 * @return java.lang.String
	 */
	public static String native2unicode(String s) {
		if (s == null || s.length() == 0) {
			return null;
		}
		byte[] buffer = new byte[s.length()];

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 0x100) {
				return s;
			}
			buffer[i] = (byte) s.charAt(i);
		}
		return new String(buffer);
	}

	/**
	 * unicode转为本地字符集 @ param String Unicode编码的字符串 @ return String
	 */
	public static String unicode2native(String s) {
		if (s == null || s.length() == 0) {
			return null;
		}
		char[] buffer = new char[s.length() * 2];
		char c;
		int j = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 0x100) {
				c = s.charAt(i);
				byte[] buf = ("" + c).getBytes();
				buffer[j++] = (char) buf[0];
				buffer[j++] = (char) buf[1];
			} else {
				buffer[j++] = s.charAt(i);
			}
		}
		return new String(buffer, 0, j);
	}

	/**
	 * 对字符串中制定的字符进行替换
	 * 
	 * @param inString
	 * @param oldPattern
	 * @param newPattern
	 * @return
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (inString == null) {
			return null;
		}
		if (oldPattern == null || newPattern == null) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();
		// output StringBuffer we'll build up
		int pos = 0; // Our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		// remember to append any characters to the right of a match
		return sbuf.toString();
	}

	/**
	 * 替换输入字符串中的HTML字符（如：< > ' \ \r \n）
	 * 
	 * @param old
	 * @return
	 */
	public static String replaceHtml(String old) {
		String rt = replace(old, "&", "&amp;");
		rt = replace(rt, "<", "&lt;");
		rt = replace(rt, ">", "&gt;");
		rt = replace(rt, "'", "&#39;");
		rt = replace(rt, "\"", "&quot;");
		rt = replace(rt, "\r", "&#xd;");
		rt = replace(rt, "\n", "&#xa;");
		return rt;
	}

	/**
	 * sql操作时，单引号(')是一个关键字。如果插入或查询时的字段值里有单引号， 会出错。必须将字段值里的单引号转换成两个单引号。
	 * 
	 * @param old
	 *            源字符串
	 * @return 转化后的字符串
	 */
	public static String replaceSql(String old) {
		String rt = replace(old, "'", "''");
		return rt;
	}

	/**
	 * 将list转换成数组String[]
	 * 
	 * @param list
	 * @return
	 */
	public static String[] list2StrArray(ArrayList list) {

		String strArray[] = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			strArray[i] = (String) list.get(i);
		}
		return strArray;
	}

	/**
	 * 将list组装成striing 格式如：('a','b')
	 * 
	 * @param list
	 * @return
	 */
	public static String getSqlList(String[] list) {
		StringBuffer strBuffer;
		strBuffer = new StringBuffer();
		strBuffer.append("(");
		strBuffer.append("'" + list[0] + "'");
		int i = 1;
		int len = list.length;
		while (i < len) {
			strBuffer.append(",");
			strBuffer.append("'" + list[i++] + "'");
		} // end while
		strBuffer.append(")");
		return strBuffer.toString();

	}

	/**
	 * 检查对象是否为数字型字符串。
	 * 只能检查正整数
	 */
	public static boolean isNumeric(Object obj) {
		if (obj == null) {
			return false;
		}
		String str = obj.toString();
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 通过正则表达式,可检查小数、负数
	 * @param obj
	 * @return
     */
	public static boolean isNumber(Object obj){
		if (obj == null) {
			return false;
		}
		String str = obj.toString();
		Boolean strResult = str.matches("-?[0-9]+.*[0-9]*");
		if(strResult == true) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查指定的字符串列表是否不为空。
	 */
	public static boolean isNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

	/**
	 * 把通用字符编码的字符串转化为汉字编码。
	 */
	public static String unicodeToChinese(String unicode) {
		StringBuilder out = new StringBuilder();
		if (!isEmpty(unicode)) {
			for (int i = 0; i < unicode.length(); i++) {
				out.append(unicode.charAt(i));
			}
		}
		return out.toString();
	}

	/**
	 * 过滤不可见字符
	 */
	public static String stripNonValidXMLCharacters(String input) {
		if (input == null || ("".equals(input)))
			return "";
		StringBuilder out = new StringBuilder();
		char current;
		for (int i = 0; i < input.length(); i++) {
			current = input.charAt(i);
			if ((current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF))
					|| ((current >= 0xE000) && (current <= 0xFFFD)) || ((current >= 0x10000) && (current <= 0x10FFFF)))
				out.append(current);
		}
		return out.toString();
	}

	/**
	 * 检查指定的字符串是否为空。
	 * <ul>
	 * <li>SysUtils.isEmpty(null) = true</li>
	 * <li>SysUtils.isEmpty("") = true</li>
	 * <li>SysUtils.isEmpty("   ") = true</li>
	 * <li>SysUtils.isEmpty("abc") = false</li>
	 * </ul>
	 * 
	 * @param value
	 *            待检查的字符串
	 * @return true/false
	 */
	public static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static String join(Object array[], char separator) {
		if (array == null)
			return null;
		else
			return join(array, separator, 0, array.length);
	}

	public static String join(Object array[], char separator, int startIndex, int endIndex) {
		if (array == null)
			return null;
		int noOfItems = endIndex - startIndex;
		if (noOfItems <= 0)
			return "";
		StringBuilder buf = new StringBuilder(noOfItems * 16);
		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex)
				buf.append(separator);
			if (array[i] != null)
				buf.append(array[i]);
		}

		return buf.toString();
	}

	/**
	 * 产生随机的三位数
	 * 
	 * @return
	 */
	public static String getRegNum() {
		Random rad = new Random();
		int randomNum = 100000 + rad.nextInt(899999);
		return randomNum + "";
	}

	/**
	 * 
	 * @param str待过滤字符串（除汉字字母数字下划线以外的字符都会被替换成*）
	 * @return
	 */
	public static String filterSpecialCharacter(String str) {
		return str.replaceAll("[^\\u4e00-\\u9fa5\\w]", "*");
	}

	public static void main(String[] args) {
		String s = getRegNum();
		System.out.println(s);
	}

	private static int LowerOfThree(int first, int second, int third) {
		int min = Math.min(first, second);

		return Math.min(min, third);
	}

	private static int Levenshtein_Distance(String str1, String str2) {
		int[][] Matrix;
		int n = str1.length();
		int m = str2.length();

		int temp = 0;
		char ch1;
		char ch2;
		int i = 0;
		int j = 0;
		if (n == 0) {
			return m;
		}
		if (m == 0) {

			return n;
		}
		Matrix = new int[n + 1][m + 1];

		for (i = 0; i <= n; i++) {
			// 初始化第一列
			Matrix[i][0] = i;
		}

		for (j = 0; j <= m; j++) {
			// 初始化第一行
			Matrix[0][j] = j;
		}

		for (i = 1; i <= n; i++) {
			ch1 = str1.charAt(i - 1);
			for (j = 1; j <= m; j++) {
				ch2 = str2.charAt(j - 1);
				if (ch1 == ch2) {
					temp = 0;
				} else {
					temp = 1;
				}
				Matrix[i][j] = LowerOfThree(Matrix[i - 1][j] + 1, Matrix[i][j - 1] + 1, Matrix[i - 1][j - 1] + temp);
			}
		}
		for (i = 0; i <= n; i++) {
			for (j = 0; j <= m; j++) {
				System.out.println(" {0} :" + Matrix[i][j]);
			}
			System.out.println("");
		}

		return Matrix[n][m];
	}

	public static double LevenshteinDistancePercent(String str1, String str2) {
		// int maxLenth = str1.Length > str2.Length ? str1.Length : str2.Length;
		int val = Levenshtein_Distance(str1, str2);
		return 1 - (double) val / Math.max(str1.length(), str2.length());
	}

}
