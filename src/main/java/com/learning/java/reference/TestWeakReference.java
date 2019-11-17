package com.learning.java.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author sangyue1
 * @Date 2019/6/17 23:35
 */
public class TestWeakReference {
	private static ReferenceQueue<Object> rq = new ReferenceQueue<>();

	public static void main(String[] args) throws InterruptedException {
		int _1M = 1024 * 1024;
		Object value = new Object();
		Map<Object, Object> map = new HashMap<>();
		for (int i = 0; i < 10000; i++) {
			byte[] bytes = new byte[_1M];
			WeakReference<byte[]> wr = new WeakReference<>(bytes, rq);
			map.put(wr, value);
			printSize();
		}

		/*Thread t = new Thread(() -> {
			try {
				int cnt = 0;
				WeakReference<byte[]> k;
				for (; (k = (WeakReference<byte[]>) rq.poll()) != null; ) {
					System.out.println((cnt++) + "回收了:" + k);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
		t.start();
		t.join();*/
		System.out.println("map.size->" + map.size());
	}

	private static void printSize() {
		try {
			int cnt = 0;
			WeakReference<byte[]> k;
			for (; (k = (WeakReference<byte[]>) rq.poll()) != null; ) {
				System.out.println((cnt++) + "回收了:" + k);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
