package com.learning.java.threadlocal;

/**
 * @Author sangyue1
 * @Date 2019/5/29 16:34
 */
public class ThreadLocalMapDemo {
	public static void main(String[] args) {
		ThreadLocal t1 = new ThreadLocal();
		t1.set("A");
		t1.set("B");

		ThreadLocal t2 = new ThreadLocal();
		t2.set("C");
		t2.set("D");

		Thread.currentThread();
	}
}
