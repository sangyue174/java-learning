package com.learning.java.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 获取泛型父类参数Demo
 *
 * @Author sangyue1
 * @Date 2019/6/26 10:12
 */
public class SuperGenericParamDemo extends GenericType<Entry> {
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		System.out.println(SuperGenericParamDemo.class.getClass());
		SuperGenericParamDemo demo = new SuperGenericParamDemo();
		String value = demo.getValue();
		System.out.printf("Class %s 中 value 的值为 %s.\n", demo.getGenericClass().getSimpleName(), value);

		// 反射获取静态字段值
		Entry entry = new SubEntry();
		System.out.println(entry.getClass().getField("value").get(null));
	}
}

class Entry {
	public final static String value = "entry-Value";
}

class SubEntry extends Entry {
	public final static String value = "subEntry-Value";
}

/**
 * 抽象泛型Type
 *
 * @param <M> entry subclass
 */
abstract class GenericType<M extends Entry> {
	private Class<M> genericClass;

	protected GenericType() {
		genericClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	public Class getGenericClass() {
		return genericClass;
	}

	public String getValue() {
		try {
			return (String) genericClass.getField("value").get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

/**
 * 获取super泛型class类型Utils
 */
class GenericsUtils {
	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager<Book>
	 *
	 * @param clazz The class to introspect
	 * @return the first generic declaration, or <code>Object.class</code> if cannot be determined
	 */
	public static Class getSuperClassGenricType(Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager<Book>
	 *
	 * @param clazz clazz The class to introspect
	 * @param index the Index of the generic ddeclaration,start from 0.
	 */
	public static Class getSuperClassGenricType(Class clazz, int index)
			throws IndexOutOfBoundsException {
		//返回表示此 Class 所表示的实体类的 直接父类(带泛型) 的 Type。注意，是直接父类
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		// 返回表示此类型实际类型参数的Type对象的数组, 当有多个泛型类时，要注意数组长度
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
}
