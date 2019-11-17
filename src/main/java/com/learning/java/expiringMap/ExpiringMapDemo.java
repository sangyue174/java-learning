package com.learning.java.expiringMap;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import java.util.concurrent.TimeUnit;

/**
 * ExpiringMapDemo
 *
 * @Author sangyue1
 * @Date 2019/9/5 21:00
 */
public class ExpiringMapDemo {
	public static void main(String[] args) throws InterruptedException {
		ExpiringMap map = ExpiringMap.builder()
				.variableExpiration()
				.expirationPolicy(ExpirationPolicy.CREATED)
				.build();
		map.put("token", "value", 2, TimeUnit.SECONDS);
		System.out.println(map.get("token"));
		Thread.sleep(3000);
		System.out.println(map.get("token"));
	}
}
