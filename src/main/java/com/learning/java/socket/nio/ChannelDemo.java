package com.learning.java.socket.nio;

import com.learning.java.classloader.GetClassPathDemo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * ChannelDemo
 *
 * @Author sangyue1
 * @Date 2019/12/4 9:31
 */
public class ChannelDemo {
	private static String classPath = GetClassPathDemo.getClassPathBySystem();

	public static void main(String[] args) throws IOException {
		/**
		 * 使用{@link Files#newByteChannel(java.nio.file.Path, java.nio.file.OpenOption...)} 读取文件并输出到控制台
		 */
		//filesReadDemo();
		/**
		 * 使用{@link Files#newByteChannel(java.nio.file.Path, java.nio.file.OpenOption...)} copy文件
		 */
		filesCopyDemo();


	}

	/**
	 * 使用{@link Files#newByteChannel(java.nio.file.Path, java.nio.file.OpenOption...)} 读取文件并输出到控制台
	 *
	 * @throws IOException
	 */
	private static void filesReadDemo() throws IOException {
		Charset charset = Charset.forName("UTF-8");
		// 读取application.properties文件到控制台
		try (FileChannel channel = (FileChannel) Files.newByteChannel(Paths.get(classPath, "application.properties"),
				StandardOpenOption.READ)) {
			ByteBuffer byteBuffer = ByteBuffer.allocate(10);
			while (channel.read(byteBuffer) > 0) {
				byteBuffer.flip();
				System.out.print(charset.decode(byteBuffer));
				byteBuffer.clear();
			}
		}
	}

	/**
	 * 使用{@link Files#newByteChannel(java.nio.file.Path, java.nio.file.OpenOption...)} copy文件
	 *
	 * @throws IOException
	 */
	private static void filesCopyDemo() throws IOException {
		// 读取application.properties文件到控制台
		try (FileChannel sourceChannel = (FileChannel) Files.newByteChannel(Paths.get(classPath, "application.properties"),
				StandardOpenOption.READ);
		     FileChannel targetChannel = (FileChannel) Files.newByteChannel(Paths.get(classPath, "application-copy.properties"),
				     StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
			ByteBuffer byteBuffer = ByteBuffer.allocate(10);
			while (sourceChannel.read(byteBuffer) > 0) {
				byteBuffer.flip();
				targetChannel.write(byteBuffer);
				byteBuffer.clear();
			}
		}
	}
}
