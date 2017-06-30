/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 * 
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.szkingdom.ssm.util;

import sun.security.provider.SHA;

import java.security.MessageDigest;
import java.util.Date;

/**
 * SHA1 class
 *
 * 计算公众平台的消息签名接口.
 */
class SHA1 {

	/**
	 *
	 * @param appSecret
	 * @param nonce
	 * @param curTime
	 * @return
	 * @throws AesException
     */
	public static String getSHA1(String appSecret, String nonce, String curTime) throws AesException {
		try {
			String[] array = new String[] { appSecret, nonce, curTime };
			StringBuffer sb = new StringBuffer();
			// 字符串排序
			//Arrays.sort(array);
			for (int i = 0; i < 3; i++) {
				sb.append(array[i]);
			}
			String str = sb.toString();
			if (str == null) {
				return null;
			}

			// SHA1签名生成
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte[] digest = md.digest();

			StringBuffer hexStr = new StringBuffer();
			String shaHex = "";
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexStr.append(0);
				}
				hexStr.append(shaHex);
			}
			return hexStr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.ComputeSignatureError);
		}
	}


	//another method

	public static String getCheckSum(String appSecret, String nonce, String curTime) {
		return encode("sha1", appSecret + nonce + curTime);
	}
	private static String encode(String algorithm, String value) {
		if (value == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(value.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static void main(String[] args) throws AesException {

		String appKey = "efabec6ca0048ba015339a824ca1ae3e";
		String appSecret = "d559fd9d91ab";
		String nonce =  String.valueOf(Math.random() * 1000000);
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum1 = SHA1.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

		String checkSum2 = SHA1.getSHA1(appSecret,nonce,curTime);

		System.out.println(checkSum1);
		System.out.println(checkSum2);
		System.out.println(EncryptCodeUtil.hexSHA1(appSecret+nonce+curTime));


	}



}
