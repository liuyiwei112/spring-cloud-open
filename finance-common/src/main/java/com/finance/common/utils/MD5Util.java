package com.finance.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	/**
	 * 获取一个文件的MD5值
	 * 
	 * @param file
	 *            文件句柄
	 * @return 返回一个md5字符串
	 */
	public static String getFileMD5(File file) {
		if (!file.exists() || !file.isFile()) {
			return null;
		}

		MessageDigest digest = null;
		FileInputStream in = null;

		byte buffer[] = new byte[1024];
		int len;

		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) > 0) {
				digest.update(buffer, 0, len);
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}

	/**
	 * 通过MD5比较两个文件是否相同
	 * 
	 * @param filePath1
	 * @param filePath2
	 * @return
	 */
	public static boolean isSameMd5(String filePath1, String filePath2) {
		File file1 = new File(filePath1);
		File file2 = new File(filePath2);
		return isSameMD5(file1, file2);
	}

	/**
	 * 通过MD5比较两个文件是否相同
	 * 
	 * @param file1
	 * @param file2
	 * @return
	 */
	public static boolean isSameMD5(File file1, File file2) {
		String md5_1 = getFileMD5(file1);
		String md5_2 = getFileMD5(file2);
		return md5_1.equals(md5_2);
	}

	/**
	 * 获取一个字符串的MD5值
	 */
	public static String StringMD5(String usString) {

		try {

			// 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）

			MessageDigest messageDigest = MessageDigest.getInstance("MD5");

			// 输入的字符串转换成字节数组
			byte[] inputByteArray = usString.getBytes();
			// inputByteArray是输入字符串转换得到的字节数组
			messageDigest.update(inputByteArray);

			// 转换并返回结果，也是字节数组，包含16个元素

			byte[] resultByteArray = messageDigest.digest();

			// 字符数组转换成字符串返回

			return byteArrayToHex(resultByteArray);

		} catch (NoSuchAlgorithmException e) {

			return null;

		}

	}

	public static String byteArrayToHex(byte[] byteArray) {

		// 首先初始化一个字符数组，用来存放每个16进制字符

		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray = new char[byteArray.length * 2];

		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;

		for (byte b : byteArray) {

			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];

			resultCharArray[index++] = hexDigits[b & 0xf];

		}

		// 字符数组组合成字符串返回

		return new String(resultCharArray);
	}
	
	/**
	 * 32位大写MD5加密
	 * @param b
	 * @return
	 */
//	public static String uppercaseStringMd5(String s) {  
//	    try {  
//	        // Create MD5 Hash  
//	        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");  
//	        digest.update(s.getBytes());  
//	        byte messageDigest[] = digest.digest();  
//	                                  
//	        return toHexString(messageDigest);  
//	    } catch (NoSuchAlgorithmException e) {  
//	        e.printStackTrace();  
//	    }  	                          
//	    return "";  
//	}  
	
	/**
	 * 32位大写MD5加密
	 * @param b
	 * @return
	 */
	public static String toHexString(byte[] b) {
		 char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
			        'A', 'B', 'C', 'D', 'E', 'F' };  
	    //String to  byte  
	    StringBuilder sb = new StringBuilder(b.length * 2);
	    for (int i = 0; i < b.length; i++) {    
	        sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);    
	        sb.append(HEX_DIGITS[b[i] & 0x0f]);    
	    }    
	    return sb.toString();    
	}
}
