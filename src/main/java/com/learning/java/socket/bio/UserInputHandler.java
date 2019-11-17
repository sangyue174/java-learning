package com.learning.java.socket.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author sangyue1
 * @Date 2019/11/6 19:33
 */
public class UserInputHandler implements Runnable {

	private ChatClient chatclient;

	public UserInputHandler(ChatClient chatClient) {
		this.chatclient = chatClient;
	}

	/**
	 *
	 */
	@Override
	public void run() {
		try {
			//等待用户输入的消息
			BufferedReader consoleReader = new BufferedReader(
					new InputStreamReader(System.in)
			);
			while (true) {
				String input = consoleReader.readLine();
				//向服务器发送消息
				chatclient.send(input);
				//检查用户是否准备退出
				if (chatclient.readyToQuit(input)) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
