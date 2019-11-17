package com.learning.java.sort;

/**
 * Sort interface
 *
 * @Author sangyue1
 * @Date 2019/6/3 11:17
 */
public interface Sort<T extends Comparable<T>> {
	void sort(T[] values);

	static <T> T[] of(T... values) {
		return values;
	}
}
