package com.learning.java.JVM;

import java.util.ArrayList;
import java.util.List;

/**
 * HeapOOMDemo
 * <p>
 * -Xms20m
 * -Xmx20m
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=/Users/xx/Documents
 *
 * @Author sangyue1
 * @Date 2019/11/10 15:12
 */
public class HeapOOMDemo {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>(10);
		while (true) {
			list.add("1");
		}
	}
}
