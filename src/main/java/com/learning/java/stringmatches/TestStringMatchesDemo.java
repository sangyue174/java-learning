package com.learning.java.stringmatches;

/**
 * @Author sangyue1
 * @Date 2019/8/29 10:48
 */
public class TestStringMatchesDemo {
	public static void main(String[] args) {
		String value = "";
		String pattern = "(?i)part|usc";
		System.out.println("String matches result is " + !value.matches(pattern));

		Object obj = null;
		System.out.println(String.valueOf(obj) + "->此处null的类型是" + String.valueOf(obj).getClass());
		System.out.println(obj);
	}
}
