package com.learning.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadPoolDemo
 *
 * @Author sangyue1
 * @Date 2019/11/15 9:49
 */
public class ThreadPoolDemo {
	public static void main(String[] args) {
		// corePoolSize为3, maximumPoolSize为3的线程池
		ExecutorService ex = Executors.newFixedThreadPool(3);
		// 循环3次
		int times = 3;

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
	 * 非打断性异常执行
	 *
	 * @param times
	 * @param ex
	 */
	private static void executeWithNoInterrupt(int times, ExecutorService ex) {
		execute(times, ex, () -> {
			for (; ; ) {
				if (Thread.currentThread().isInterrupted()) {
					System.out.println(Thread.currentThread().getName() + "被shutdownNow打断");
					return;
				}
			}
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
