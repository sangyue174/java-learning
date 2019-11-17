package com.learning.java.reflection;

import java.lang.reflect.Method;

/**
 * AccessibleObjectDemo
 *
 * @Author sangyue1
 * @Date 2019/10/27 18:31
 */
public class AccessibleObjectDemo {
	public static void main(String[] args) throws NoSuchMethodException {
		// 测试反射setAccessible true & false的性能开销
		int times = 100000;
		String value = "hello world";
		// 该方法不要放在循环内部, 消耗性能比较大
		Method method = String.class.getDeclaredMethod("toString");

		// test1: 直接调用
		execute(times, () -> value.toString());

		// 反射预热操作
		execute(times, () -> {
			try {
				method.invoke(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		// test2: setAccessible true 反射测试
		execute(times, () -> {
			try {
				method.setAccessible(true);
				method.invoke(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		// test3: setAccessible false 反射测试
		execute(times, () -> {
			try {
				method.setAccessible(false);
				method.invoke(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private static void execute(int times, Runnable runnable) {
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			runnable.run();
		}
		long t2 = System.currentTimeMillis();
		System.out.printf("执行 %s 次消耗时间 %s ms. \n", times, t2 - t1);
	}
}
