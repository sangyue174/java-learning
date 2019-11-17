package com.learning.java.JVM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * GarbageCleanerDemo
 *
 * @Author sangyue1
 * @Date 2019/10/19 20:13
 */
public class GarbageCleanerDemo {
	private static Logger log = LoggerFactory.getLogger(GarbageCleanerDemo.class);

	public static void main(String[] args) throws IOException {
		// Serial GC
		//
		String str = "aa";
		System.in.read();
		// refer to org.springframework.boot.web.servlet.error.DefaultErrorAttributes.addStackTrace
		/*Exception e = new RuntimeException("run excep");
		StringWriter stackTrace = new StringWriter();
		e.printStackTrace(new PrintWriter(stackTrace));
		stackTrace.flush();
		System.out.println(stackTrace);*/
	}
}
