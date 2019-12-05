package com.learning.java.JVM;

import java.util.ArrayList;
import java.util.List;

/**
 * JVMHookDemo
 * <p>
 * -Xms20m -Xmx20m
 *
 * @Author sangyue1
 * @Date 2019/12/5 14:45
 */
public class JVMHookDemo {
	public static void main(String[] args) {
		// 正常JVM退出测试
		//naturalExitTest();
		// OOM退出测试
		OOMExitTest();
	}

	private static void naturalExitTest() {
		// JVM退出前(System.exit(0), kill, Ctrl+C都在该范围中)调用hook
		Runtime.getRuntime().addShutdownHook(
				new Thread(() -> System.out.println(Thread.currentThread().getName() + ", hook:JVM正常退出之前调用1")));
		sun.misc.SharedSecrets.getJavaLangAccess().registerShutdownHook(2, false,
				() -> System.out.println(Thread.currentThread().getName() + ", hook:JVM正常退出之前调用2"));

		System.out.println("主线程结束");
	}

	private static void OOMExitTest() {
		// JVM退出前(System.exit(0), kill, Ctrl+C都在该范围中)调用hook
		Runtime.getRuntime().addShutdownHook(
				new Thread(() -> System.out.println(Thread.currentThread().getName() + ", hook:JVM OOM退出之前调用1")));
		sun.misc.SharedSecrets.getJavaLangAccess().registerShutdownHook(2, false,
				() -> System.out.println(Thread.currentThread().getName() + ", hook:JVM OOM退出之前调用2"));

		// 模仿OOM
		List<byte[]> list = new ArrayList<>(10);
		int _1M = 1024 * 1024;
		byte[] bytes = new byte[_1M];
		while (true) {
			list.add(bytes);
		}
	}

}
