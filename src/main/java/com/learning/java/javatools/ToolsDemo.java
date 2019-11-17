package com.learning.java.javatools;

import sun.tools.attach.WindowsVirtualMachine;

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
		WindowsVirtualMachine.list().forEach(System.out::println);
	}
}
