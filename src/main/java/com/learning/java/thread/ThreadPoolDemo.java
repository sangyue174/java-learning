package com.learning.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadPoolDemo
 *
 * @Author sangyue1
 * @Date 2019/11/15 9:49
 */
public class ThreadPoolDemo {
	public static void main(String[] args) throws InterruptedException {
		// 测试shutdown和shutdownNow
		//testShutdownAndShutdownNow();
		// 测试守护线程池
		testDaemonThreadPool();
	}

	private static void testShutdownAndShutdownNow() {
		// corePoolSize为3, maximumPoolSize为3的线程池
		ExecutorService ex = Executors.newFixedThreadPool(3);
		// 循环3次
		int times = 1;

		// 如下选一种测试
		executeWithInterruptedException(times, ex);
		//executeWithNoInterrupt(times, ex);

		// 如下选一种测试
		//ex.shutdown();
		ex.shutdownNow();
		System.out.println("主线程已结束");

		/** 结论:
		 *  shutdown和shutdownNow并不会直接中断线程
		 *  如果线程正在执行
		 *  shutdown设置interrupt标志
		 *      shutdown不会设置interrupt标志的原因在于它会循环判断线程池中每个worker是否可以java.util.concurrent.ThreadPoolExecutor.Worker#tryLock(),
		 *      因为worker在执行操作之前会调用java.util.concurrent.ThreadPoolExecutor.Worker#lock()来设置state,
		 *      所以在unlock之前, tryLock会失败, 所以不会设置interrupt标志.
		 *  shutdownNow会设置interrupt标志,
		 *      如果线程正在执行sleep join等可以抛出InterruptedException的操作, 程序会抛出该异常,
		 *      但是并不是立马中断线程, 如果想在业务code中实现中断的效果, 那么需要单独判断interrupt标志 -> isInterrupted()然后自行中断.
		 **/
	}

	/**
	 * 通过ThreadFactory设置守护线程的线程池, 创建的线程都为守护线程, 当主线程结束后, daemon线程自动结束, 不管线程池是否运行完
	 *
	 * @throws InterruptedException
	 */
	private static void testDaemonThreadPool() throws InterruptedException {
		ExecutorService ex = Executors.newFixedThreadPool(3, new DaemonThreadFactory());
		int times = 1;
		executeWithInterruptedException(times, ex);
		//ex.shutdown();
		ex.shutdownNow();
		System.out.println("主线程结束");
	}

	/**
	 * 非打断性异常执行
	 *
	 * @param times
	 * @param ex
	 */
	private static void executeWithNoInterrupt(int times, ExecutorService ex) {
		execute(times, ex, () -> {
			System.out.println(Thread.currentThread().getName() + "开始运行");
			if (Thread.currentThread().isInterrupted()) {
				System.out.println(Thread.currentThread().getName() + "被shutdownNow打断");
				return;
			}
			System.out.println(Thread.currentThread().getName() + "线程正在执行");
		});
	}

	/**
	 * 打断性异常执行
	 *
	 * @param times
	 * @param ex
	 */
	private static void executeWithInterruptedException(int times, ExecutorService ex) {
		execute(times, ex, () -> {
			try {
				System.out.println(Thread.currentThread().getName() + "开始运行");
				Thread.sleep(3000);
				System.out.println(Thread.currentThread().getName() + "结束运行");
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println(Thread.currentThread().getName() + "被打断");
				return;
			}
		});

	}

	/**
	 * 最终执行方法
	 *
	 * @param times
	 * @param ex
	 * @param runnable
	 */
	private static void execute(int times, ExecutorService ex, Runnable runnable) {
		for (int i = 1; i <= times; i++) {
			ex.execute(runnable);
		}
	}
}
