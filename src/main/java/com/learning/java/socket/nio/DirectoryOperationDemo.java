package com.learning.java.socket.nio;

import com.learning.java.classloader.GetClassPathDemo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * DirectoryOperationDemo
 *
 * @Author sangyue1
 * @Date 2019/12/1 19:59
 */
public class DirectoryOperationDemo {
	public static void main(String[] args) {
		// 在classPath下创建sub-dir目录.
		// 获取当前classPath
		Stream.of(GetClassPathDemo.getClassPathBySystem())
				.map(dirPath -> Paths.get(dirPath, "sub-dir"))
				.forEach(subdir -> {
					try {
						if (Files.exists(subdir)) {// 已经存在
							System.out.printf("目录 [%s] 已经存在.", subdir);
							return;
						}
						Files.createDirectory(subdir);
						System.out.printf("新目录 [%s] 已经创建.", subdir);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

	}
}
