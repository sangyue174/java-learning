package com.learning.java.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author sangyue1
 * @Date 2019/5/27 17:39
 */
public class TestMapKeyDemo {
	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "A");
		System.out.println(map.get(1));
		System.out.println(map.get(new Key(1)));
	}

	private static class Key {
		private Integer value;
		public Key(Integer value) {
			this.value = value;
		}
		@Override
		public boolean equals(Object o) {
			if (this == o) { return true; }
			if (o instanceof Integer) { return value.intValue() == (Integer) o; }
			Key key = (Key) o;
			return Objects.equals(value, key.value);
		}
		@Override
		public int hashCode() {
			return this.value;
		}
	}



	/*private static class Key {
		private Number value;

		public Key(Number value) {
			this.value = value;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o instanceof Number) {
				return value.intValue() == (Integer) o;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Key key = (Key) o;
			return Objects.equals(value, key.value);
		}

		@Override
		public int hashCode() {
			return this.value.intValue();
		}
	}*/
}
