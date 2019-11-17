package com.learning.java.classloader;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * ClassLoaderDemo
 *
 * @Author sangyue1
 * @Date 2019/10/11 9:37
 */
public class ClassLoaderDemo {
	public static void main(String[] args) throws MalformedURLException {
		//System.out.println(System.getProperty("java.protocol.handler.pkgs"));
		URL url = new URL("https://www.baidu.com");
		System.out.println(url);
		//Unsafe.getUnsafe();
		//Unsafe.getUnsafe().freeMemory(1);
		ClassLoader current = Thread.currentThread().getContextClassLoader();
		ClassLoader parent = current.getParent();
		ClassLoader superParent = parent.getParent();
		System.out.printf("current is %s, parent is %s, super parent is %s. %n", current, parent, superParent);

		System.out.println("String.class loader is " + String.class.getClassLoader());
		System.out.println("system class loader is " + ClassLoader.getSystemClassLoader());
	}

}
