package com.learning.java.generic;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * class toGenericString & getGenericSuperclass()
 *
 * @Author sangyue1
 * @Date 2019/10/30 15:53
 */
public class ClassGenericStringDemo {
	private static Map<String, Integer> map = Maps.newHashMap();

	public static void main(String[] args) {
		System.out.println(map.getClass().toGenericString());
		System.out.println(new B("value").getClass().getGenericSuperclass());

		System.out.println(new B("value").getValue());
		System.out.println(new B("value").getSuperValue());
	}
}

class A<E> {
	protected E value;

	public A(E value) {
		this.value = value;
	}
}

class B extends A<String> {
	private String value;

	public B(String value) {
		super("super" + value);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public String getSuperValue() {
		return super.value;
	}
}
