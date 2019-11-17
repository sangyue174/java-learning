package com.learning.java.socket.bio;

import java.io.*;
import java.net.Socket;

public class ChatClient {

	private final String QUIT = "quit";
	private final String DEFAULT_SERVER_HOST = "127.0.0.1";
	private final int DEFAULT_SERVER_PORT = 8888;
	private Socket socket;
	private BufferedReader bufferedReader = null;
	private BufferedWriter bufferedWriter = null;

	/**
	 * 发送消息给服务器
	 *
	 * @param msg 用户发送的消息
	 * @throws IOException
	 */
	public void send(String msg) throws IOException {
		if (!socket.isOutputShutdown()) {//确定socket的输出流未关闭
			bufferedWriter.write(msg + "\n");
			bufferedWriter.flush();
		}
	}

	/**
	 * 从服务器接收消息
	 *
	 * @return
	 * @throws IOException
	 */
	public String receive() throws IOException {
		String msg = null;
		if (!socket.isInputShutdown()) {//确定socket的输入流未关闭
			msg = bufferedReader.readLine();
		}
		return msg;
	}

	/**
	 * 检查用户是否准备退出
	 *
	 * @param msg 用户发送的消息
	 * @return
	 */
	public boolean readyToQuit(String msg) {
		return QUIT.equals(msg);
	}

	/**
	 * 关闭socket
	 */
	public void close() {
		if (bufferedWriter != null) {
			try {//关闭bufferedWriter的同时也关闭socket
				System.out.println("关闭socket");
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 创建实例、IO流、处理用户输入以及读取服务器转发的消息
	 */
	public void start() {
		try {
			//创建socket实例
			socket = new Socket(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);
			//创建IO流
			bufferedReader = new BufferedReader(
					new InputStreamReader(socket.getInputStream())
			);
			bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())
			);
			//创建额外的线程处理用户的输入
			new Thread(new UserInputHandler(this)).start();
			//读取服务器转发的消息
			String msg = null;
			while ((msg = receive()) != null) {
				System.out.println(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public static void main(String[] args) {
		ChatClient chatClient = new ChatClient();
		chatClient.start();
	}
}
