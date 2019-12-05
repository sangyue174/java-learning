package com.learning.java.thread;

import java.util.concurrent.ThreadFactory;

/**
 * DaemonThreadFactory
 *
 * @Author sangyue1
 * @Date 2019/12/3 15:30
 */
public class DaemonThreadFactory implements ThreadFactory {
	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setDaemon(true);
		return thread;
	}
}
