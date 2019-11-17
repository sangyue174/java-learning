package com.learning.java.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * UnsafeUtil
 *
 * @Author sangyue1
 * @Date 2019/10/13 17:15
 */
public class UnsafeUtil {
	public static void main(String[] args) {
		getUnsafe().freeMemory(1);
	}

	private static Unsafe getUnsafe() {
		Unsafe unsafe = null;
		Field field = null;
		try {
			field = Unsafe.class.getDeclaredField("theUnsafe");
		} catch (NoSuchFieldException e) {
			try {
				Constructor<Unsafe> constructor = Unsafe.class.getDeclaredConstructor();
				constructor.setAccessible(true);
				unsafe = constructor.newInstance();
			} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e2) {
				throw new Error(e2);
			}
		}
		if (field != null) {
			field.setAccessible(true);
			try {
				unsafe = (Unsafe) field.get(null);
			} catch (IllegalAccessException e) {
				throw new Error(e);
			}
		}
		return unsafe;
	}
}
