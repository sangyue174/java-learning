package com.learning.java.metadata;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * MetadataDemo
 *
 * @Author sangyue1
 * @Date 2019/10/21 14:25
 */
public class MetadataDemo {
	public static void main(String[] args) {
		echo();
		System.out.println("user.dir" + System.getProperty("user.dir"));
		System.out.println(Stream.of(1, 2, 3).map(value -> value + "str").collect(Collectors.joining(", ")));

	}

	public static void echo(String... strs) {
		System.out.println("content is " + Arrays.toString(strs));
	}
}
