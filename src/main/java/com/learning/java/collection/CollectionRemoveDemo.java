package com.learning.java.collection;

import java.util.Iterator;
import java.util.List;

/**
 * @Author sangyue1
 * @Date 2019/5/31 15:16
 */
public class CollectionRemoveDemo {
	public static void main(String[] args) throws Throwable {
		List<Integer> list;
		int size;
		Iterator<Integer> it;

		// first
		/*list = new ArrayList<>(Arrays.asList(1, 2, 3));
		for (int i = 0; i < list.size(); i++) {
			list.remove(i);
		}
		System.out.println(list);*/

		// second
		/*list = new ArrayList<>(Arrays.asList(1, 2, 3));
		size = list.size();
		for (int i = 0; i < size; i++) {
			list.remove(i);
		}
		System.out.println(list);*/

		// third
		/*list = new ArrayList<>(Arrays.asList(1, 2, 3));
		size = list.size();
		for (int i = 0; i < size; i++) {
			list.remove(0);
		}
		System.out.println(list);*/

		/*// fourth
		list = new ArrayList<>(Arrays.asList(1, 2, 3));
		for (int i : list) {
			list.remove(i);
		}
		System.out.println(list);

		// fifth
		list = new ArrayList<>(Arrays.asList(1, 2, 3));
		it = list.iterator();
		while (it.hasNext()) {
			it.remove();
		}
		System.out.println(list);

		// sixth
		list = new ArrayList<>(Arrays.asList(1, 2, 3));
		it = list.iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
		System.out.println(list);*/

		/*//seventh, 通过调用Iterator的forEachRemaining方法操作实现remove
		list = new ArrayList<>(Arrays.asList(1, 2, 3));
		it = list.iterator();

		// 创建lookup并反射设置lookupClass为Iterator class
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		Field f = MethodHandles.Lookup.class.getDeclaredField("lookupClass");
		f.setAccessible(true);
		f.set(lookup, Iterator.class);

		// 设置Iterator类的forEachRemaining方法的返回值类型和入参类型
		MethodType mt = MethodType.methodType(void.class, Consumer.class);
		// 调用Iterator接口中的forEachRemaining方法,而不是调用ArrayList中的, 因为ArrayList中该方法不支持迭代器remove操作,导致异常,
		// 而Iterator接口中的default实现支持该操作
		MethodHandle methodHandle = lookup.findSpecial(Iterator.class, "forEachRemaining", mt, Iterator.class);
		// 反射调用, 参数为Consumer型lambda表达式
		methodHandle.invoke(it, (Consumer<Integer>) t -> {
			System.out.println("Consumer Value::" + t);
			it.remove();
		});

		System.out.println(list);*/
	}

}
