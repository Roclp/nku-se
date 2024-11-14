package net;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class ServerChannel extends JFrame {
	// Text area for displaying contents
	private JTextArea jta = new JTextArea();

	public static void main(String[] args) {
		new ServerChannel();
	}

	private String readMsg(SelectionKey key) {
		SocketChannel channel = null;

		channel = (SocketChannel) key.channel();
		// 设置buffer缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		// 假如客户端关闭了通道，这里在对该通道read数据，会发生IOException，捕获到Exception后，关闭掉该channel，取消掉该key
		try {
			int count = channel.read(buffer);
			StringBuffer buf = new StringBuffer();
			// 如果读取到了数据
			if (count > 0) {
				// 让buffer翻转，把buffer中的数据读取出来
				buffer.flip();
				buf.append(new String(buffer.array(), 0, count));
			}
			return buf.toString();
		} catch (IOException e) {
			key.cancel();
			try {
				channel.socket().close();
				channel.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}

	}

	private void writeMsg(SelectionKey key) {
		try {
			SocketChannel channel = (SocketChannel) key.channel();
			Object attachment = key.attachment();
			// 获取key的值之后，要把key的值置空，避免影响下一次的使用
			key.attach("");
			channel.write(ByteBuffer.wrap(attachment.toString().getBytes()));
			key.interestOps(SelectionKey.OP_READ);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Selector selector;

	public ServerChannel() {
		// Place text area on the frame
		setLayout(new BorderLayout());
		add(new JScrollPane(jta), BorderLayout.CENTER);

		setTitle("Server");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); // It is necessary to show the frame here!

		try {
			// Create a server socket
			ServerSocketChannel serverSocketc = ServerSocketChannel.open();
			serverSocketc.configureBlocking(false);
			ServerSocket serverSocket = serverSocketc.socket();
			serverSocket.bind(new InetSocketAddress(8000));
			selector = Selector.open();
			serverSocketc.register(selector, SelectionKey.OP_ACCEPT);
			jta.append("Server started at " + new Date() + '\n');

			// Listen for a connection request
			while (selector.isOpen()) {
				int count = selector.select();
				if (count > 0) {
					Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
					while (iterator.hasNext()) {
						SelectionKey selectionKey = iterator.next();
						if (selectionKey.isAcceptable()) {

							ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
							// 接受socket
							SocketChannel socket = serverChannel.accept();
							socket.configureBlocking(false);
							socket.register(selector, SelectionKey.OP_READ);
							iterator.remove();
							continue;

						}
						if (selectionKey.isValid() && selectionKey.isReadable()) {
							SocketChannel sc = (SocketChannel) selectionKey.channel();

							jta.append(sc.socket().getRemoteSocketAddress().toString());
							String r = this.readMsg(selectionKey);
							if (r == null) {
								continue;
							}

							// Receive radius from the client
							double radius = Double.parseDouble(r);

							// Compute area
							Double area = radius * radius * Math.PI;

							// Send area back to the client
							selectionKey.attach(area.toString());
							selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_WRITE);

							jta.append("Radius received from client: " + radius + '\n');

						}
						if (selectionKey.isValid() && selectionKey.isWritable()) {
							jta.append("Area found: " + selectionKey.attachment() + '\n');
							this.writeMsg(selectionKey);

						}
					}
				}
			}

			// Create data input and output streams

		} catch (IOException ex) {
			ex.printStackTrace();
			System.err.println(ex);
		}
	}
}
