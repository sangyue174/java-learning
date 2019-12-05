package com.learning.java.classloader;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * GetClassPath
 * 获取classpath
 *
 * @Author sangyue1
 * @Date 2019/11/3 14:44
 */
public class GetClassPathDemo {
	private static final String classPathProperty = System.getProperty("java.class.path");

	public static void main(String[] args) {
		Class clazz = GetClassPathDemo.class;
		System.out.println("项目根目录:" + System.getProperty("user.dir"));
		System.out.println("classpath绝对目录:" + clazz.getProtectionDomain().getCodeSource().getLocation().getPath());
		System.out.println("classpath绝对目录(by class resource):" + clazz.getResource("/").getPath());
		System.out.println("classpath绝对目录(by classLoader resource):" + clazz.getClassLoader().getResource("").getPath());
		System.out.println("项目所有classpath(包含jar)" + classPathProperty);
		System.out.println("当前项目classpath:" + getClassPathBySystem());
	}

	/**
	 * 通过System.property来获取当前classPath
	 *
	 * @return
	 */
	public static String getClassPathBySystem() {
		String classPath = Stream.of(classPathProperty.split(File.pathSeparator))
				.map(Paths::get) // String -> Path
				.filter(Files::isDirectory) // 是目录
				.map(Path::toString) // 转为String
				.findFirst()
				.orElse("");
		return classPath;
	}
}
