package com.learning.java.reflection;

/**
 * Array对象成员类型
 *
 * @Author sangyue1
 * @Date 2019/11/3 16:06
 */
public class ArrayComponentTypeDemo {
	public static void main(String[] args) {
		Integer[] a = new Integer[3];
		System.out.println(a.getClass().getComponentType());
	}
}
