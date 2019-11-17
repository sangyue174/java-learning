package com.learning.java.sort;

import java.util.Set;
import java.util.TreeSet;

import static org.apache.coyote.http11.Constants.a;

/**
 * @Author sangyue1
 * @Date 2019/7/30 20:24
 */
public class TestSort implements Runnable {
	public <S, T> T getObject(S a, TestSuper<S, T> converter) {
		return converter.convert(a);
	}

	public static void main(String[] args){
		TestSort sort = new TestSort();
		String aa = sort.getObject(11, integer -> integer.toString());
		System.out.println(aa);

		/*Thread t1 = new Thread(() -> {
			try {
				System.out.println("inner "+Thread.currentThread().getState());
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " 继续运行");
		});
		t1.start();
		Thread.sleep(1000);
		System.out.println(t1.getState());
		t1.interrupt();
		System.out.println(t1.getState());
		System.out.println(t1.getState());
		System.out.println(t1.getState());
		System.out.println(t1.getState());
		System.out.println(t1.getState());
		System.out.println(t1.getState());
		System.out.println(t1.getState());*/

		/*Set<String> set = new TreeSet();

		System.out.println(set.add("1"));
		System.out.println(set.add("1"));*/
		System.out.println(new TestSort().convertStr("222", String.class));
	}

	private static void print(Object obj){
		System.out.println(obj);
	}

	@Override
	public void run() {
		System.out.println("inner "+Thread.currentThread().getState());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public <T> T convertStr(Object param, Class<T> clazz) {
		if (clazz.isInstance(param)) {
			return (T) param;
		}
		return null;
	}

}
