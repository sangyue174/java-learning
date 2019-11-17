package com.learning.java.generic;

/**
 * @Author sangyue1
 * @Date 2019/11/3 14:44
 */
public class GetClassPathDemo {
	public static void main(String[] args) {
		Class clazz = GetClassPathDemo.class;
		System.out.println("项目根目录:" + System.getProperty("user.dir"));
		System.out.println("classpath绝对目录:" + clazz.getProtectionDomain().getCodeSource().getLocation().getPath());
		System.out.println("classpath绝对目录(by class resource):" + clazz.getResource("/").getPath());
		System.out.println("classpath绝对目录(by classLoader resource):" + clazz.getClassLoader().getResource("").getPath());
		System.out.println("项目所有classpath(包含jar)" + System.getProperty("java.class.path"));
	}
}
