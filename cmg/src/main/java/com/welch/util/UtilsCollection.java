package com.welch.util;

import com.welch.exception.FlagException;
import org.springframework.security.crypto.codec.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.lang.management.ManagementFactory;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;

public class UtilsCollection {

	/*
	 * Apply the date in the format YYYMMDD to Strings such as file names where specified.
	 */
	public synchronized static String applyDateToString(String str, String date, String dateFormat) {
		if (str.contains(dateFormat)) {
			String newStr = str.replaceAll(dateFormat, date);

			return newStr;
		} else {
			return str;
		}
	}

	/*
	 * Take in a HashMap and return an ordered array of keys as int's
	 */
	public static int[] sortKeyList(HashMap<?, ?> map) {
		Object[] keys = map.keySet().toArray();
		int[] keysInt = new int[keys.length];
		for (int i = 0; i < keys.length; i++) {
			keysInt[i] = Integer.parseInt(keys[i].toString());
		}
		Arrays.sort(keysInt);

		return keysInt;
	}

	/*
	 * Get the process ID of the JVM .
	 */
	public synchronized static String getProcessId() {
		String processPid = ManagementFactory.getRuntimeMXBean().getName();
		String pid[] = processPid.split("@");
		return pid[0];
	}

	/*
	 * Prints a standardised title containing specified text.
	 */
	public synchronized static void printTitle(String title, JavaLogger log) {
		String boxChar = "=";
		String box = "";
		for (int x = 0; x < title.length() + 4; x++) {
			box += boxChar;
		}
		String word = boxChar + " " + title + " " + boxChar;
		log.writeToLog("LM_INFO", "");
		log.writeToLog("LM_INFO", box);
		log.writeToLog("LM_INFO", word);
		log.writeToLog("LM_INFO", box);
	}

	public synchronized static boolean interpretFlag(String flag, String positive, String negative) throws FlagException {
		if (flag.equalsIgnoreCase(positive)) {
			return true;
		} else if (flag.equalsIgnoreCase(negative)) {
			return false;
		} else {
			throw new FlagException();
		}
	}

	public static String encryptOrDecrypt(String src, int mode) {

		if (src == null) {
			return null;
		}

		String DESKEY = "sparcabcd12345craps67890";
		String IVKEY = "sparc123";

		SecureRandom sr = new SecureRandom();
		DESedeKeySpec dks;

		try {
			dks = new DESedeKeySpec(DESKEY.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			SecretKey securekey = keyFactory.generateSecret(dks);
			IvParameterSpec iv = new IvParameterSpec(IVKEY.getBytes());
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			cipher.init(mode, securekey, iv, sr);

			if (mode == Cipher.ENCRYPT_MODE) {
				return new String(Hex.encode(cipher.doFinal(src.getBytes())));
			}

			if (mode == Cipher.DECRYPT_MODE) {
				return new String(cipher.doFinal(Hex.decode(src)));
			}

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return src;
	}

	public static void main(String[] args){
		String password = "m0nday";
		String encryptedPassword = encryptOrDecrypt(password, Cipher.ENCRYPT_MODE);
		System.out.println(encryptedPassword);
	}
}
