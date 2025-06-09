package com.learning.java.javatools;

import sun.tools.attach.HotSpotVirtualMachine;

import java.io.IOException;

/**
 * ToolsDemo
 * jdk/lib/tools.jar
 *
 * @Author sangyue1
 * @Date 2019/10/17 20:33
 */
public class ToolsDemo {
	public static void main(String[] args) throws IOException {
		HotSpotVirtualMachine.list().forEach(System.out::println);
	}
}
