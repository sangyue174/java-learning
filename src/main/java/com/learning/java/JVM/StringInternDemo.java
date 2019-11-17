package com.learning.java.JVM;

/**
 * StringInternDemo
 *
 * @Author sangyue1
 * @Date 2019/10/14 11:30
 */
public class StringInternDemo {
	public static void main(String[] args) {
		// 1. 普通string比较
		String str1 = "aaa";
		System.out.printf("[Normal]: \"aaa\".intern() == \"aaa\": %s \n", str1.intern() == str1);

		// 2. new String()比较
		String str2 = new String("bbb");
		System.out.printf("[New]: new String(\"bbb\").intern() == \"bbb\": %s \n", str2.intern() == str2);

		// 3. char[] new String()比较
		char[] chars = {'c', 'c', 'c'};
		String str3 = new String(chars);
		System.out.printf("[New chars]: new String({'c','c','c'}).intern() == \"ccc\": %s\n", str3.intern() == str3);

		// 3.1 char[] new String()again 比较
		String str33 = new String(chars);
		System.out.printf("[Again new chars]: new String({'c','c','c'}).intern() == \"ccc\": %s\n", str33.intern() == str33);

		// 4. StringBuidler no append 比较
		String str4 = new StringBuilder("ddd").toString();
		System.out.printf("[No append]: new StringBuilder(\"ddd\").toString().intern() == \"ddd\": %s \n", str4.intern() == str4);

		// 4.1 StringBuidler have append 比较
		String str44 = new StringBuilder("dd").append("d").toString();
		System.out.printf("[One append(value same)]: new StringBuilder(\"dd\").append(\"d\").toString().intern() == \"ddd\": %s \n", str44.intern() == str44);

		// 5 StringBuilder have append 比较
		String str5 = new StringBuilder("ee").append("e").toString();
		System.out.printf("[One append(value different)]: new StringBuilder(\"ee\").append(\"e\").toString().intern() == \"eee\": %s \n", str5.intern() == str5);
	}
}
