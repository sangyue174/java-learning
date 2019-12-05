package com.learning.java.thread;

import sun.misc.SharedSecrets;
import sun.misc.VM;
import sun.nio.ch.Interruptible;

/**
 * @Author sangyue1
 * @Date 2019/11/5 13:50
 */
public class CustomerizedInterruptDemo {
	public static void main(String[] args) throws InterruptedException {
		Interruptible interruptible = (t) -> System.out.println(t.getName() + "被中断");
		Thread t1 = new Thread(() -> {
			/**
			 * 设置Thread的block属性为interruptible
			 * {@link Thread#blockedOn(Interruptible)}
			 */
			SharedSecrets.getJavaLangAccess().blockedOn(Thread.currentThread(), interruptible);
			System.out.println(Thread.currentThread().getName() + "执行内容");
			// 模拟线程正在执行
			for (; ; ) {
				// 获取interrupt状态并清空
				if (Thread.interrupted()) {
					System.out.println(Thread.currentThread().getName() + "由于被中断跳出工作任务");
					break;
				}
			}
		}, "Thread1");

		t1.start();
		// 50ms后打断线程
		Thread.sleep(50);
		// main线程打断thread1线程
		//t1.interrupt();

		// main线程等待thread1执行完毕
		t1.join();
		System.out.println("全部完成");
		//System.out.println(VM.isBooted());
	}

	static void blockedOn(Interruptible interruptible) {         // package-private
		SharedSecrets.getJavaLangAccess().blockedOn(Thread.currentThread(), interruptible);
	}
}
