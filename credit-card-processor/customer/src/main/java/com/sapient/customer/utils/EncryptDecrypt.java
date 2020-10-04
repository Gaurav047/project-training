package com.sapient.customer.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecrypt {
	
	public static String encrypt(String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec("RandomInitVector".getBytes(StandardCharsets.UTF_8));
			SecretKeySpec skeySpec = new SecretKeySpec(
					"Bar12345Bar12345".getBytes(StandardCharsets.UTF_8), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			byte[] original = Base64.getEncoder().encode(encrypted);
			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec("RandomInitVector".getBytes(StandardCharsets.UTF_8));
			SecretKeySpec skeySpec = new SecretKeySpec(
					"Bar12345Bar12345".getBytes(StandardCharsets.UTF_8), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
}
