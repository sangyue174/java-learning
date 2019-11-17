package com.learning.java.sort;

import java.util.Arrays;

/**
 * BubbleSort
 * 稳定排序, 比较相邻元素大小, 将最大或者最小的元素移到一端
 * 平均复杂度n^2
 *
 * @Author sangyue1
 * @Date 2019/6/3 11:21
 */
public class BubbleSort<T extends Comparable<T>> implements Sort<T> {
	@Override
	public void sort(T[] values) {
		if (values == null || values.length <= 0) {
			return;
		}
		int n = values.length;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				T temp = values[i];
				if (values[i].compareTo(values[j]) > 0) {
					values[i] = values[j];
					values[j] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		BubbleSort sort = new BubbleSort();
		Integer[] values;
		//values = Sort.of(3, 5, 2, 1, 4);
		values = Sort.of(5, 4, 3, 2, 1);
		sort.sort(values);
		System.out.println(Arrays.toString(values));
	}
}
