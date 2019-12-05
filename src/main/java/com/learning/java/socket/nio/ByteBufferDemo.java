package com.learning.java.socket.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * ByteBufferDemo
 *
 * @Author sangyue1
 * @Date 2019/12/1 17:26
 */
public class ByteBufferDemo {
	public static void main(String[] args) {
		Charset charset = Charset.forName("UTF-8");
		// position = limit = 10
		ByteBuffer byteBuffer = ByteBuffer.allocate(10);
		System.out.println(byteBuffer);

		// position+1
		byteBuffer.put((byte) 'a');
		byteBuffer.put((byte) 'b');
		byteBuffer.put((byte) 'c');
		System.out.println(byteBuffer);

		// limit = position, position = 0, 变为读模式, 注意每次操作完写操作后, 都需要再执行一次flip才可以拿到最新写入的数据
		// 一般来说, flip后的内容是一次性的, 一般来说, flip后如果需要再次对buffer进行修改操作, 需要执行clear操作
		byteBuffer.flip();
		byteBuffer.put((byte) 'd');
		byteBuffer.put((byte) 'e');
		byteBuffer.put((byte) 'f');
		// 这里再put是有问题的
		//byteBuffer.put((byte) 'g');
		System.out.println(byteBuffer);
		byteBuffer.rewind();

		// 解码
		CharBuffer charBuffer = charset.decode(byteBuffer);

		System.out.println(byteBuffer);
		System.out.println(charBuffer);

		// position = 0
		//byteBuffer.rewind();
	}
}
