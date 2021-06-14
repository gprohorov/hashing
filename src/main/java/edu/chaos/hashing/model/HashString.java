
package edu.chaos.hashing.model;

/*
@author   george
@project   hashing
@class  RandomTest
@version  1.0.0
@since 31.01.2021 - 17.31
*/
public class HashString {
	private String source;
	private String result;
	private long time;

	public HashString() {
	}

	public HashString(String source, String result, long time) {
		this.source = source;
		this.result = result;
		this.time = time;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "HashString{" +
				"source='" + source + '\'' +
				", result='" + result + '\'' +
				", time=" + time +
				'}';
	}
}
