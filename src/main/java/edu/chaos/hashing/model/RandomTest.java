package edu.chaos.hashing.model;

/*
@author   george
@project   hashing
@class  RandomTest
@version  1.0.0
@since 31.01.2021 - 17.31
*/



public class RandomTest {
	private long distinctResultCount;
	private int sourceCount;
	private long time;

	public RandomTest() {
	}

	public RandomTest(long distinctResultCount, int sourceCount, long time) {
		this.distinctResultCount = distinctResultCount;
		this.sourceCount = sourceCount;
		this.time = time;
	}

	public long getDistinctResultCount() {
		return distinctResultCount;
	}

	public void setDistinctResultCount(long distinctResultCount) {
		this.distinctResultCount = distinctResultCount;
	}

	public int getSourceCount() {
		return sourceCount;
	}

	public void setSourceCount(int sourceCount) {
		this.sourceCount = sourceCount;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "RandomTest{" +
				"distinctResultCount=" + distinctResultCount +
				", sourceCount=" + sourceCount +
				", time=" + time +
				'}';
	}
}
