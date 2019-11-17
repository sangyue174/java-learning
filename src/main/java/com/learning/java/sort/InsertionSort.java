package com.learning.java.sort;

import java.util.Arrays;

/**
 * InsertionSort
 * 稳定排序, 在原先已经有序的小序列基础上依次插入新的元素, 最开始的元素就是第一个元素.
 * 平均复杂度n^2
 *
 * @Author sangyue1
 * @Date 2019/6/3 14:00
 */
public class InsertionSort<T extends Comparable<T>> implements Sort<T> {
	@Override
	public void sort(T[] values) {
		if (values == null || values.length <= 0) {
			return;
		}
		int n = values.length;
		for (int i = 1; i < n; i++) {
			for (int j = i; j > 0; j--) {
				T temp = values[j];
				if (values[j - 1].compareTo(values[j]) > 0) {//后边的值比前边的大, 进行插入
					values[j] = values[j - 1];
					values[j - 1] = temp;
				} else {
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		InsertionSort sort = new InsertionSort();
		Integer[] values;
		//values = Sort.of(3, 5, 2, 1, 4);
		values = Sort.of(5, 4, 3, 2, 1);
		sort.sort(values);
		System.out.println(Arrays.toString(values));
	}
}
