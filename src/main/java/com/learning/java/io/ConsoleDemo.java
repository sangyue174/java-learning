package com.learning.java.io;

import java.io.Console;

/**
 * System.Console
 *
 * @Author sangyue1
 * @Date 2019/11/7 22:07
 */
public class ConsoleDemo {
	public static void main(String[] args) {
		System.out.println("please input your name");
		Console console = System.console();
		String name = console.readLine();
		console.format("hello, %s", name);
		console.flush();
	}
}
