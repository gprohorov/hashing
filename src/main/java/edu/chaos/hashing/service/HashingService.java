package edu.chaos.hashing.service;

/*
  @author   george
  @project   hashing
  @class  HashingService
  @version  1.0.0
  @since 31.01.2021 - 17.31
*/

import edu.chaos.hashing.util.BitsUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HashingService {

	private static final int N = 512;
	private static final String SINGLE_BIT = "10000000";
	private static final int[] SHIFT_CONSTANTS = new int[]{7, 12, 17, 22};
	public static final int BLOCK_SIZE = 128;

	public static String getHashString(String source) {
		StringBuilder sourceBits = convertStringToBits(source);
		String paddedBits = padBitString(sourceBits);
		List<String> blocks = splitToBlocks(paddedBits); //block size 128
		boolean[] lastHash = generateInitialVector(sourceBits);
		for (int i = 0, blocksSize = blocks.size(); i < blocksSize; i++) {
			boolean[] boolBlock = BitsUtils.getBooleansFromBits(blocks.get(i));
			boolean[] shiftedLastHash = BitsUtils.shiftLeftCyclic(lastHash, SHIFT_CONSTANTS[i % 4]);
			boolean[] compressedBlock = compressBlock(boolBlock, shiftedLastHash);
			lastHash = hashBlock(compressedBlock);
		}
		return toHex(lastHash);
	}

	private static boolean[] generateInitialVector(StringBuilder sourceBits) {
		String first64 = sourceBits.substring(0, 64);
		String last64 = sourceBits.substring(sourceBits.length() - 65, sourceBits.length() - 1);
		String composed128 = first64 + last64;
		return   CelAutomatesService
				.transformComposedVectorHorizontally(BitsUtils.getBooleansFromBits(composed128));
	}

	private static String toHex(boolean[] lastHash) { //TODO ideally reverse traverse order
		StringBuilder resultString = new StringBuilder();
		int length = lastHash.length;
		for (int i = 0; i < length; i += 4) {
			StringBuilder tempString = new StringBuilder();

			for (int j = 0; j < Math.min(length - i, 4); j++) {
				tempString.append(lastHash[i + j] ? "1" : "0");
			}
			resultString.append(BitsUtils.getHexFromBinary(tempString.toString()));
		}
		return resultString.toString();
	}

	private static boolean[] compressBlock(boolean[] block, boolean[] previousHash) {
		return BitsUtils.xorArrays(block, previousHash);
	}

	private static boolean[] hashBlock(boolean[] block) {
		return CelAutomatesService.transformBlockVertically(block);
	}

	private static String padBitString(StringBuilder sourceBits) {
		int initialLength = sourceBits.length();
		int updateLength = initialLength + 8;
		sourceBits.append(SINGLE_BIT);

		if (updateLength + 64 % N != 0) {
			sourceBits.append(getZeroBits(calculateZerosQuantity(updateLength)));
		}

		sourceBits.append(convertLengthIn64Bit(initialLength));
		return sourceBits.toString();
	}

	private static int calculateZerosQuantity(int updateLength) {
		int mod = updateLength / 448;
		return (mod * 512) + 448 - updateLength;
	}

	private static String convertLengthIn64Bit(int length) {
		String lengthBits = BitsUtils.mapToBinaryString(length, 32);
		if (lengthBits.length() < 32) {
			lengthBits = getZeroBits(32 - lengthBits.length()) + lengthBits; // pad to 32 length
		}
		return getZeroBits(32) + lengthBits; // pad to 64
	}

	private static String getZeroBits(int i) {
		return IntStream.range(0, i).mapToObj(el -> "0").collect(Collectors.joining());
	}

	private static List<String> splitToBlocks(String sourceBits) {
		String[] blocks = BitsUtils.splitBits(sourceBits, BLOCK_SIZE);
		return Arrays.stream(blocks).collect(Collectors.toList());
	}

	private static StringBuilder convertStringToBits(String source) {
		return BitsUtils.getBitsString(source);
	}
}
