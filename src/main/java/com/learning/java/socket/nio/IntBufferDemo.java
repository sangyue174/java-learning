package com.learning.java.socket.nio;

import java.nio.IntBuffer;

/**
 * @Author sangyue1
 * @Date 2019/11/28 9:22
 */
public class IntBufferDemo {
	public static void main(String[] args) {
		IntBuffer buffer = IntBuffer.allocate(10);
		buffer.put(1);
		buffer.put(2);
		buffer.put(3);

		buffer.flip();
		System.out.println("remain:" + buffer.remaining());
		while (buffer.hasRemaining()) {
			System.out.println(buffer.get());
		}
		buffer.rewind();
		System.out.println("rewind");
		while (buffer.hasRemaining()) {
			System.out.println(buffer.get());
		}
		// 重置position limit capacity的位置
		/*buffer.clear();

		buffer.put(4);
		buffer.flip();
		while (buffer.hasRemaining()) {
			System.out.println(buffer.get());
		}*/
	}
}
