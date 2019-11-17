package com.learning.java.stream;


import com.learning.java.stream.optional.Person;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author sangyue1
 * @Date 2019-04-04 9:44 PM
 */
public class OptionalDemo {
	private static List<PersonModel> list = null;

	static {
		PersonModel wu = new PersonModel("wu qi", 18, "男");
		PersonModel zhang = new PersonModel("zhang san", 19, "男");
		PersonModel wang = new PersonModel("wang si", 20, "女");
		PersonModel zhao = new PersonModel("zhao wu", 20, "男");
		PersonModel chen = new PersonModel("chen liu", 21, "男");
		list = Arrays.asList(wu, zhang, wang, zhao, chen);
	}

	public static List<PersonModel> getData() {
		return list;
	}

	public static void main(String[] args) {
		List<PersonModel> data = OptionalDemo.getData();
		/*List<PersonModel> collect = data
				.stream()
				.filter(person -> ("男".equals(person.getSex()) && person.getAge() < 20))
				.collect(toList());
		System.out.println(collect);

		List<String> collect1 = data.stream().map(person -> person.getName()).collect(toList());
		System.out.println(collect1);

		List<String> collect2 = data.stream().map(person -> {
			System.out.println(person.getName());
			return person.getName();
		}).collect(toList());*/

		//IntStream intStream = IntStream.range(1, 3);
		//intStream.toArray();
		Integer[] array = new Integer[]{2, 3, 4};
		// forEach
		//Stream.of(array).forEach(OptionalDemo::dowork);
		// toArray, strs == strs1
		String[] strs = Stream.of("haha", "lala").toArray(a -> new String[a]);
		String[] strs1 = Stream.of("haha", "lala").toArray(String[]::new);
		// to list
		List<Integer> list = Stream.of(array).collect(Collectors.toList());
		List<String> listStr = Stream.of(array).map(item -> "a+".concat(String.valueOf(item))).collect(Collectors.toCollection(ArrayList::new));
		System.out.println(listStr);

		String aa = Stream.of("haha", "lala").collect(Collectors.joining());
		System.out.println(aa);
		List<String> listFlat = Stream.of("haha", "lala").flatMap(key -> Stream.of(key.concat("-flat"))).collect(Collectors.toList());
		System.out.println(listFlat);

		Stream.of("one", "two", "three", "four")
				.filter(e -> e.length() > 3)
				.peek(e -> System.out.println("Filtered value: " + e))
				.map(String::toUpperCase)
				.peek(e -> System.out.println("Mapped value: " + e))
				.collect(Collectors.toList());

		String reduceStr = Stream.of("haha", "lala", "yaya").reduce((a, b) -> a.concat("+reduce+").concat(b)).orElse("haha");
		System.out.println(reduceStr);
		String reduceStr1 = Stream.of("haha", "lala", "yaya").reduce("mama", (a, b) -> a.concat("+reduce+").concat(b));
		System.out.println(reduceStr1);
		//System.out.println(Optional.ofNullable(null).orElse("nicai"));
		//System.out.println(Optional.ofNullable("optional").map(String::toUpperCase));
		//System.out.println(Optional.ofNullable("optional1").map(a -> a.toUpperCase()));

		/**
		 * optional test
		 */
		Optional<Person.Insurance> insuranceOptional = Optional.of(Person.Insurance.builder().name(null).build());
		Optional<Person.Car> carOptional = Optional.of(Person.Car.builder().insurance(insuranceOptional).build());
		Optional<Person> personOptional = Optional.of(Person.builder().car(carOptional).build());

		String name = personOptional.flatMap(Person::getCar)
				.flatMap(Person.Car::getInsurance)
				.map(Person.Insurance::getName)
				.orElse("unknown");

		System.out.println("name is " + name);

		// find the max length of "one, two, three, four"
		System.out.println(Stream.of("one", "two", "three", "four").mapToInt(String::length).max().getAsInt());

		/**
		 * generate stream by yourself
		 * use generate || iterate
		 */
		// generate stream, combine limit to use, random int
		Supplier<Integer> random = () -> new Random().nextInt(10);
		List<Integer> generateList = Stream.generate(random).limit(10).collect(Collectors.toList());
		System.out.println(generateList);

		// iterate stream, combine limit to use
		List<Integer> listIterate = Stream.iterate(0, n -> n + 3).limit(10).collect(Collectors.toList());
		System.out.println(listIterate);
	}

	public void dowork(Object aa) {
		System.out.println(aa);
	}

	public void dowork() {
		System.out.println("1111");
	}

	public String toUpperCase() {
		return "UPPER";
	}

}
