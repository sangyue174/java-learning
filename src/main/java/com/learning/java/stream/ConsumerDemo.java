package com.learning.java.stream;

import java.util.function.Consumer;

/**
 * ConsumerDemo
 *
 * @Author sangyue1
 * @Date 2019-04-08 11:03 PM
 */
public class ConsumerDemo {
	public static void main(String[] args) {
		Consumer<Integer> consumer = ConsumerDemo::println;
		consumer.andThen(a -> System.out.println("and then:" + a))
				.accept(123);

		//Supplier<Long> supplier = System::currentTimeMillis;
		//supplier.get();
		CustomerizedFunction<String, String, Integer, Integer> customerizedFunction = (r, u, v) -> r + u + v + "test";
		System.out.println(customerizedFunction.apply("first", 1, 2));

		CustomerizedFunction<String, String, String, String> customerizedFunction2 = ConsumerDemo::println;
		System.out.println(customerizedFunction2.apply("first", "second", "third"));
	}

	public static void println(Integer a) {
		System.out.println("print:" + a);
	}
	public static String println(String a, String b, String c) {
		return "print:" + a + b + c;
	}

	@FunctionalInterface
	public interface CustomerizedFunction<T, R, U, V> {
		T apply(R r, U u, V v);

		default T apply2(R r) {
			return null;
		}
	}
}
