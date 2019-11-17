package com.learning.java.hashcode;

import java.util.Objects;

/**
 * @Author sangyue1
 * @Date 2019/8/21 14:50
 */
public class TestClassHashCodeDemo {
	public static void main(String[] args) {
		Super subObj = new Sub("1");
		Super superObj = new Super("1");

		System.out.printf("Sub.equals(Super):%s \n", subObj.equals(superObj));
		System.out.printf("Sub.equals(Sub):%s \n", subObj.equals(subObj));
		System.out.printf("Super.equals(Super):%s \n", superObj.equals(superObj));
		System.out.printf("Super.equals(Sub):%s \n", superObj.equals(subObj));
		System.out.printf("SubHashCode == SuperHashCode:%s \n", subObj.hashCode() == superObj.hashCode());
		System.out.printf("SubHashCode == SubHashCode):%s \n", subObj.hashCode() == subObj.hashCode());
		System.out.printf("SuperHashCode == SuperHashCode):%s \n", superObj.hashCode() == superObj.hashCode());
		System.out.printf("SuperHashCode == SubHashCode):%s \n", superObj.hashCode() == subObj.hashCode());
	}

	public static class Super {
		private String value;

		public Super(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null) {
				return false;
			}
			if (o.getClass() == Sub.class) {
				Sub that = (Sub) o;
				return Objects.equals(value, that.getValue());
			}
			if (o.getClass() == Super.class) {
				Super that = (Super) o;
				return Objects.equals(value, that.value);
			}
			return false;
		}

		@Override
		public int hashCode() {
			return Objects.hash(value);
		}
	}

	public static class Sub extends Super {
		private String valueSub;

		public Sub(String value) {
			super(value);
			valueSub = value;
		}

		public String getValueSub() {
			return valueSub;
		}
		/*@Override
		public boolean equals(Object o) {
			return super.equals(o);
		}

		@Override
		public int hashCode() {
			return super.hashCode();
		}*/
	}
}
