package com.learning.java.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @Author sangyue1
 * @Date 2019/6/17 20:24
 */
public class TestWeakReferenceDemo {
	private static ReferenceQueue<Integer> rq = new ReferenceQueue<>();

	public static void main(String[] args) throws InterruptedException {
		//Integer obj = new Integer(666);
		//Object obj = new Object();
		WeakReference<Integer> reference = new WeakReference(new Integer(666777), rq);
		System.out.println("reference 对象实例:" + reference);
		System.out.println("reference 对象GC前的被引用对象:" + reference.get());
		System.out.println("referenceQueue 对象GC前的入队对象:" + rq.poll());
		//obj = null;
		System.gc();
		Thread.sleep(50);
		System.out.println("reference 对象GC后的被引用对象:" + reference.get());
		System.out.println("referenceQueue 对象GC后的入队对象:" + rq.poll());
	}
}
