package com.learning.java.threadlocal;

/**
 * IngeritableThreadLocalDemo
 *
 * @Author sangyue1
 * @Date 2019/9/24 0:31
 */
public class IngeritableThreadLocalDemo {
	public static void main(String[] args) {
		ThreadLocal itl = new InheritableThreadLocal();
		itl.set(11);
		System.out.println("Main : " + itl.get());
		new Thread(() -> System.out.printf("%s : %s\n", Thread.currentThread().getName(), itl.get()))
				.start();
	}
}
