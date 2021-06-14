package edu.chaos.hashing.util;

/*
  @author   george
  @project   hashing
  @class  BitsUtils
  @version  1.0.0
  @since 17.03.2021 - 16.07
*/

import java.nio.charset.StandardCharsets;


public class BitsUtils {

	public static boolean[] getBooleans(byte[] start) {
		boolean[] resultArray = new boolean[start.length * 8];
		for (int i = 0; i < start.length; i++) {
			byte byteNumber = start[i];
			String binaryString = mapToBinaryString(byteNumber);
			for (int j = 0; j < 8; j++) {
				String[] strings = binaryString.split("");
				resultArray[i * 8 + j] = strings[j].equals("1");
			}
		}
		return resultArray;
	}

	public static boolean[] getBooleans(String string) {
		byte[] bytes = string.getBytes(StandardCharsets.US_ASCII);
		return getBooleans(bytes);
	}

	public static boolean[] getBooleansFromBits(String bits) {
		boolean[] resultArray = new boolean[bits.length()];
		String[] split = bits.split("");
		for (int i = 0, splitLength = split.length; i < splitLength; i++) {
			String s = split[i];
			resultArray[i] = s.equals("1");
		}
		return resultArray;
	}

	public static String mapToBinaryString(byte i) {
		return String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
	}

	public static String mapToBinaryString(int i, int numberOfBits) {
		return String.format("%" + numberOfBits + "s", Integer.toBinaryString(i)).replace(' ', '0');
	}

	public static StringBuilder getBitsString(String string) {
		byte[] bytes = string.getBytes(StandardCharsets.US_ASCII);
		StringBuilder resultString = new StringBuilder();
		for (byte byt : bytes) {
			resultString.append(mapToBinaryString(byt));
		}
		return resultString;
	}

	public static String[] splitBits(String bits, int n) {
		return bits.split("(?<=\\G.{" + n + "})");
	}

	public static boolean[] shiftLeftCyclic(boolean[] array, int num) {
		int sourceArrayIndex = 0;
		int resultArrayIndex = 0;
		int length = array.length;
		boolean[] tempArray = new boolean[num];
		boolean[] resultArray = new boolean[length];
		for (; sourceArrayIndex < num; sourceArrayIndex++) { // n operations
			tempArray[sourceArrayIndex] = array[sourceArrayIndex];
		}
		for (; sourceArrayIndex < length; sourceArrayIndex++, resultArrayIndex++) { // array.length
			resultArray[resultArrayIndex] = array[sourceArrayIndex];
		}
		for (int i = 0; i < tempArray.length; i++, resultArrayIndex++) { // n
			resultArray[resultArrayIndex] = tempArray[i];
		}
		return resultArray;
	}

	public static boolean[] xorArrays(boolean[] block, boolean[] previousHash) {
		int length = block.length;
		boolean[] result = new boolean[length];
		for (int i = 0; i < length; i++) {
			result[i] = block[i] ^ previousHash[i];
		}
		return result;
	}

	public static String getHexFromBinary(String binary) {
		return Integer.toHexString(Integer.parseInt(binary, 2));
	}
}
