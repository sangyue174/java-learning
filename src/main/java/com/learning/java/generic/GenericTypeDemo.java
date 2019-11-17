package com.learning.java.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 通配类型(泛型)demo
 * super & extends的限制范围
 *
 * @Author sangyue1
 * @Date 2019-04-07 5:05 PM
 */
public class GenericTypeDemo {
	public static void main(String[] args) {
		List<Integer> ints = Arrays.asList(1, 2);
		lowerBounded(ints);
		List<? super Number> numbers = new ArrayList();
		upperBounded(numbers);

		List<Number> list = new ArrayList<>();
		list.addAll(Arrays.asList(1));
	}

	public static void lowerBounded(List<? extends Number> producer) {
		for (Number number : producer) {
			System.out.println(number);
		}
	}

	public static void upperBounded(List<? super Number> consumer) {
		List<Integer> ints = Arrays.asList(3, 4);
		List<Short> shorts = Arrays.asList((short) 5, (short) 6);
		consumer.addAll(ints);
		consumer.addAll(shorts);

		for (Object o : consumer) {
			System.out.println(o);
		}
	}
}
