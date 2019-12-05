package com.learning.java.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * ThreadDemo
 *
 * @Author sangyue1
 * @Date 2019/11/27 11:58
 */
public class ThreadDemo {
	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(() -> {
			List<byte[]> list = new ArrayList<>();
			int _1M = 1024 * 1024;
			while (true) {
				byte[] bytes = new byte[_1M];
				list.add(bytes);
				System.out.println(Thread.currentThread().getName() + "分配了1M空间");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread thread2 = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + "继续运行");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread1.start();
		thread2.start();
	}
}
