package com.learning.java.strings;


import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @Author sangyue1
 * @Date 2019/8/26 15:45
 */
public class TestStringUtilsDemo {
	public static void main(String[] args) {
		String s = StringUtils.join(Arrays.asList("1","2","3","4"), ",");
		System.out.println(s);
	}
}
