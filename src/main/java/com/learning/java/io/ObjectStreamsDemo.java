package com.learning.java.io;

import java.io.*;
import java.util.Arrays;

/**
 * @Author sangyue1
 * @Date 2019/11/7 22:40
 */
public class ObjectStreamsDemo {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Integer[] ints = new Integer[]{1, 2, 3};
		System.out.println("序列化之前对象内容:" + Arrays.toString(ints) + ", size为" + ints.length);
		File file = new File("object.obj");
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
		os.writeObject(ints);
		os.writeObject(ints.length);

		ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
		Integer[] intss = (Integer[]) is.readObject();
		int sizes = (int) is.readObject();
		System.out.println("序列化之后对象内容:" + Arrays.toString(intss) + ", size为" + sizes);
	}
}

class ObjectA {
	public transient Integer[] ints;
	public int size;

	public ObjectA(Integer[] ints, int size) {
		this.ints = ints;
		this.size = size;
	}
}