package com.learning.java.stream.optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * 人物(Optional模式)
 *
 * @Author sangyue1
 * @Date 2019-04-05 10:09 PM
 */
@Data
@Builder
public class Person {
	private Optional<Car> car;//人可能有车，也可能没有车，因此将这个字段声明为 Optional

	public Optional<Car> getCar() {
		return car;
	}

	/**
	 * 汽车
	 */
	@Data
	@Builder
	public static class Car {
		private Optional<Insurance> insurance;//车可能进行了保险，也可能没有保险，所以将这个字段声明为 Optional

		public Optional<Insurance> getInsurance() {
			return insurance;
		}
	}

	/**
	 * 车险
	 */
	@Data
	@Builder
	public static class Insurance {
		private String name;

		public String getName() {
			return this.name;
		}
	}
}
