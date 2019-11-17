package com.learning.java.thread;

/**
 * 获取所有线程demo
 *
 * @Author sangyue1
 * @Date 2019/10/12 11:15
 */
public class GettingAllThreadDemo {
	public static void main(String[] args) {
		getAllThread();
	}

	private static void getAllThread() {
		for (int i = 1; i <= 2; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName());
			}).start();
		}
		ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
		int noThreads = currentGroup.activeCount();
		Thread[] lstThreads = new Thread[noThreads];
		currentGroup.enumerate(lstThreads);
		for (int i = 0; i < noThreads; i++) {
			System.out.println("线程数量：" + noThreads + " 线程id：" + lstThreads[i].getId() + " 线程名称：" + lstThreads[i].getName() + " 线程状态：" + lstThreads[i].getState());
		}
	}
}
