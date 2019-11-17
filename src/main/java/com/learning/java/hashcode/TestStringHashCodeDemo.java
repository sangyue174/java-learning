package com.learning.java.hashcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author sangyue1
 * @Date 2019-04-22 11:17 PM
 */
public class TestStringHashCodeDemo {
	public static void main(String[] args) {
		//stringHashCode();

		//Integer[] integers = getIntegers(1);
		//arraysHashCode(integers);
		//System.out.println(integers.hashCode());

		EqualsAndHash test1 = new EqualsAndHash(1);
		EqualsAndHash test2 = new EqualsAndHash(2);
		System.out.printf("equals result: %s, hashcode result: %s", test1.equals(test2), test1.hashCode() == test2.hashCode());
	}

	private static class EqualsAndHash{
		private Integer value;
		public EqualsAndHash(Integer value) {
			this.value = value;
		}
		@Override
		public boolean equals(Object o) {
			return true;
			//return this.value.intValue() == ((EqualsAndHash)o).value.intValue();
		}
		@Override
		public int hashCode() {
			return value;
		}
	}

	private static Integer[] getIntegers(Integer... values){
		return values;
	}

	private static void arraysHashCode(Integer[] integers) {
		System.out.println(Objects.hash(integers));
	}

	private static void stringHashCode() {
		//String a = "12";
		//System.out.println(a.hashCode());
		String b = "22";
		System.out.println(b.hashCode());

		List<String> stockCounts = new ArrayList<>();
		stockCounts.add("1");
		stockCounts.add("2");
		String[] arrays = stockCounts.toArray(new String[stockCounts.size()]);
		for(String array:arrays){
			System.out.println(array);
		}
	}
}
