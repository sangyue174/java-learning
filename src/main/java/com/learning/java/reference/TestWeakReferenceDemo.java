package com.learning.java.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * TestWeakReferenceDemo
 *
 * @Author sangyue1
 * @Date 2019/6/17 20:24
 */
public class TestWeakReferenceDemo {
	private static ReferenceQueue rq = new ReferenceQueue<>();

	public static void main(String[] args) throws InterruptedException {
		//test1();
		//test2();
		test3();
		Reference value;
		while ((value = rq.poll()) != null) {
			System.out.println("queue content:" + value);
		}
	}

	/**
	 * 普通WeakReference对象测试
	 *
	 * @throws InterruptedException
	 */
	private static void test1() throws InterruptedException {
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

	/**
	 * listWeakReference 测试
	 * WeakReference中存放的是普通对象, 但是将该reference存放在list中
	 *
	 * @throws InterruptedException
	 */
	private static void test2() throws InterruptedException {
		List<WeakReference> list = new ArrayList<>();
		for (int i = 1; i <= 50; i++) {
			//Integer obj = new Integer(666);
			byte[] bytes = new byte[1024 * 1024];
			WeakReference<byte[]> reference = new WeakReference(bytes, rq);
			list.add(reference);
			System.out.println("第[" + i + "]次reference 对象GC前的被引用对象:" + reference.get());
			System.gc();
			Thread.sleep(50);
			System.out.println("第[" + i + "]次reference 对象GC后的被引用对象:" + reference.get());
		}
	}

	/**
	 * WeakReference List测试
	 * WeakReference中存放的是list
	 *
	 * @throws InterruptedException
	 */
	private static void test3() throws InterruptedException {
		List<byte[]> list = new ArrayList<>();
		WeakReference<List<byte[]>> reference = new WeakReference(list, rq);

		for (int i = 1; i <= 20; i++) {
			byte[] bytes = new byte[1024 * 1024];
			list.add(bytes);
			//System.out.println("第[" + i + "]次reference 对象实例:" + reference);
			System.out.println("第[" + i + "]次reference 对象GC前的被引用对象:" + reference.get());
			//System.out.println("第[" + i + "]次referenceQueue 对象GC前的入队对象:" + rq.poll());
			//System.gc();
			Thread.sleep(1000);
			System.out.println("第[" + i + "]次reference 对象GC后的被引用对象:" + reference.get());
			//System.out.println("第[" + i + "]次referenceQueue 对象GC后的入队对象:" + rq.poll());
		}
		list = null;
		System.gc();
		System.out.println("执行System.gc()");
	}
}
