package com.learning.java.socket.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * BIOServer
 * <p>
 * BIO方式向socket发送数据时, writer.write("xxx\n")后边要加上换行符, reader才可以读到
 *
 * @Author sangyue1
 * @Date 2019/11/6 16:09
 */
public class BIOServer {
	public void initBIOServer(int port) {
		String inputContent;
		System.out.println(stringNowTime() + ": serverSocket started");
		try (ServerSocket serverSocket = new ServerSocket(port);//服务端Socket
		     Socket socket = serverSocket.accept();//客户端socket
		     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
			System.out.println(stringNowTime() + ": id为" + socket.hashCode() + "的Clientsocket connected");
			int count = 1;
			while ((inputContent = reader.readLine()) != null && count <= 10) {
				System.out.println("收到id为" + socket.hashCode() + "  " + inputContent);
				writer.write("你好, 我已经收到了第" + count + "条消息");
				writer.flush();
				count++;
			}


			System.out.println("id为" + socket.hashCode() + "的Clientsocket " + stringNowTime() + "读取结束");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initBIOServer1(int port) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		BufferedReader reader = null;
		BufferedWriter writer = null;
		String msg;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println(stringNowTime() + ": serverSocket started");
			socket = serverSocket.accept();
			System.out.println(stringNowTime() + ": port为 " + socket.getPort() + " 的Clientsocket connected");
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			int count = 1;
			while ((msg = reader.readLine()) != null && count <= 10) {
				System.out.println("收到port为[" + socket.getPort() + "]" + msg + "\n");

				writer.write("你好, server已经收到了第" + count + "条消息, " + msg + "\n");
				writer.flush();
				count++;
			}

			System.out.println("port为[" + socket.getPort() + "]的Clientsocket " + stringNowTime() + "读取结束");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String stringNowTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

	public static void main(String[] args) {
		BIOServer server = new BIOServer();
		//server.initBIOServer(8888);
		server.initBIOServer1(8889);
	}
}
