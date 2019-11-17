package com.learning.java.reflection;

import java.lang.reflect.Field;

/**
 * StringReflectionDemo
 *
 * @Author sangyue1
 * @Date 2019/10/30 16:55
 */
public class StringReflectionDemo {
	/**
	 * Java 5 开始反射可以修改对象属性，即使它被 final 修饰
	 * 但是要注意final类中字段值间的相互影响, 比如hashcode的缓存
	 *
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String testContent = "Hello,World";
		String otherValue = "asang";
		// 修改前的内容输出
		System.out.println("反射修改前的 testContent = " + testContent);
		System.out.println("反射前hashcode是" + testContent.hashCode());
		//  private final char value[];
		// 获取名为 "value" 的 private 字段
		Field valueField = String.class.getDeclaredField("value");
		// 关闭访问性检查
		valueField.setAccessible(true);
		// 替换 testContent String 对象中 value[];
		valueField.set(testContent, otherValue.toCharArray());
		// 修改后的内容输出
		System.out.println("反射修改后的 testContent = " + testContent);
		System.out.println("反射后hashcode是" + testContent.hashCode());
	}
}
