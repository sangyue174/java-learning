package com.learning.java.threadlocal;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sangyue1
 * @Date 2019/5/29 16:34
 */
public class ThreadLocalMapDemo {
	public static void main(String[] args) {
		/*ThreadLocal t1 = new ThreadLocal();
		t1.set("A");
		t1.set("B");*/
		//System.out.println(t1.get());

		ThreadLocal<List<byte[]>> t2 = new ThreadLocal();
		int _1M = 1024 * 1024;
		List<byte[]> list = new ArrayList<>();
		t2.set(list);
		for (int i = 0; i < 20; i++) {
			byte[] bytes = new byte[_1M];
			list.add(bytes);
		}

		System.out.println("ThreadLocal++++++++++++++++++Value" + t2.get());
	}
}
