package com.learning.java.socket.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
	private int DEFAULT_PORT = 8888;//默认端口
	private final String QUIT = "quit";//退出指令
	private ExecutorService executorService;
	private ServerSocket serverSocket;
	private Map<Integer, Writer> connectedClients;//存储客户端信息

	public ChatServer(){
		executorService = Executors.newFixedThreadPool(5);//创建线程池
		connectedClients = new HashMap<>();
	}

	/**
	 * 客户端连接，添加一个客户端
	 * synchronized 保证线程的安全性，防止多个线程同时添加客户端
	 * @param socket 客户端的socket
	 * @throws IOException
	 */
	public synchronized void addClient(Socket socket) throws IOException {
		if(socket != null){
			int port = socket.getPort();
			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())
			);
			connectedClients.put(port, bufferedWriter);
			System.out.println("客户端["+port+"]已连接到服务器");
		}
	}
	/**
	 *  客户端断开连接，从列表中删除客户端
	 *  synchronized 保证线程安全性， 防止多个线程同时删除客户端
	 * @param socket 断开连接的客户端socket
	 * @throws IOException
	 */
	public synchronized void removeClient(Socket socket) throws IOException {
		if(socket != null){
			int port = socket.getPort();
			if(connectedClients.containsKey(port)){
				connectedClients.get(port).close();
			}
			connectedClients.remove(port);
			System.out.println("客户端["+port+"]已断开连接");
		}
	}
	/**
	 * 向服务器中其他的客户端转发消息，除了消息本身的拥有者
	 * synchronized 保证线程安全性，防止多个线程同时去转发消息，访问connectedClients
	 * @param socket 消息所属的客户端的socket
	 * @param fwdMsg 消息的具体内容
	 * @throws IOException
	 */
	public synchronized void forwardMessage(Socket socket, String fwdMsg) throws IOException {
		for(Integer id : connectedClients.keySet()){
			if(id.equals(socket.getPort())){
				Writer writer = connectedClients.get(id);
				writer.write(fwdMsg);
				writer.flush();
			}
		}
	}
	/**
	 * 如果用户发送quit则准备退出
	 * @param msg 用户返送的消息
	 * @return
	 */
	public boolean readyToQuit(String msg){
		return QUIT.equals(msg);
	}
	/**
	 * synchronized 保护serverSocket的状态
	 * 关闭serverSocket
	 */
	public synchronized void close(){
		if(serverSocket != null){
			try {
				serverSocket.close();
				System.out.println("关闭serverSocket");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 开启服务器，监听端口，并且启动ChatHandler线程
	 */
	public void start(){

		try {
			// 绑定监听端口
			serverSocket = new ServerSocket(DEFAULT_PORT);
			System.out.println("启动服务器，监听端口：" + DEFAULT_PORT + "...");

			while(true){
				//等待客户端连接
				Socket socket = serverSocket.accept();
				//创建额外的ChatHandler线程
				executorService.execute(new ChatHandler(this, socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public static void main(String[] args) {
		ChatServer chatServer = new ChatServer();
		chatServer.start();
	}
}
