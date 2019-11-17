package com.learning.java.socket.bio;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author sangyue1
 * @Date 2019/11/6 16:35
 */
public class BIOClient {

	public void initBIOClient(String host, int port) {
		String inputContent;
		String outContent;

		try (BufferedReader sysReader = new BufferedReader(new InputStreamReader(System.in));
		     Socket socket = new Socket(host, port);
		     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
			int count = 1;
			System.out.println("clientSocket started: id 为 " + socket.hashCode() + ", time " + stringNowTime());
			//while (((inputContent = sysReader.readLine()) != null) && count <= 10) {
			inputContent = stringNowTime() + ": 第" + count + "条消息: " + "hahaha" + "\n";
			writer.write(inputContent);//将消息发送给服务端
			writer.flush();

			while ((outContent = reader.readLine()) != null) {
				System.out.println("收到server消息" + outContent);
			}
			count++;
			//}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initBIOClient1(String host, int port) {
		String input;
		String msg;
		BufferedReader sysReader;
		Socket socket;
		BufferedReader reader;
		BufferedWriter writer;

		try {
			sysReader = new BufferedReader(new InputStreamReader(System.in));
			socket = new Socket(host, port);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			int count = 1;
			System.out.println("clientSocket started: port 为[" + socket.getPort() + "], time " + stringNowTime());
			while (((input = sysReader.readLine()) != null) && count <= 10) {
				input = stringNowTime() + ": 第" + count + "条消息: " + input + "\n";
				writer.write(input);//将消息发送给服务端
				writer.flush();

				while ((msg = reader.readLine()) != null) {
					System.out.println(msg);
					break;
				}
				count++;
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String stringNowTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

	public static void main(String[] args) {
		BIOClient client = new BIOClient();
		client.initBIOClient1("127.0.0.1", 8889);
	}
}