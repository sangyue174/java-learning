package com.learning.java.arrays;

import java.util.Arrays;

/**
 * @Author sangyue1
 * @Date 2019-04-22 3:03 PM
 */
public class ArrayCopyDemo {

	public static void main(String[] args) {
		Integer[] a = new Integer[]{1, 2};
		a = Arrays.copyOf(a, a.length + 1);
		a[a.length - 1] = 3;
		System.out.println(a.length);
		System.out.println(Arrays.asList(a));

		Integer[] b = new Integer[]{3, 4, 5};
		//b[1] = null;
		System.out.println(b.length);
		System.out.println(Arrays.toString(b));

		//System.arraycopy(b, 2, b, 1, 1);
		Integer[] c = new Integer[1];
		System.arraycopy(b, 0, c, 0, 1);
		System.out.println(Arrays.toString(c));
	}
}
