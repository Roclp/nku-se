package net;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClientChannel extends JFrame {
	// Text field for receiving radius
	private JTextField jtf = new JTextField();

	// Text area to display contents
	private JTextArea jta = new JTextArea();
	SocketChannel socket;

	public static void main(String[] args) {
		new ClientChannel();
	}

	public ClientChannel() {
		// Panel p to hold the label and text field
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(new JLabel("Enter radius"), BorderLayout.WEST);
		p.add(jtf, BorderLayout.CENTER);
		jtf.setHorizontalAlignment(JTextField.RIGHT);

		setLayout(new BorderLayout());
		add(p, BorderLayout.NORTH);
		add(new JScrollPane(jta), BorderLayout.CENTER);

		jtf.addActionListener(new ButtonListener()); // Register listener

		setTitle("Client");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); // It is necessary to show the frame here!

		try {
			// Create a socket to connect to the server

			socket = SocketChannel.open(); // 没有传入参数，则选一个未被占用的端口号
			socket.connect(new InetSocketAddress("localhost", 8000));
			socket.configureBlocking(false);
			jta.append("serverrealsocket " + socket.getRemoteAddress() + "\n");
			// Create an input stream to receive data from the server

		} catch (IOException ex) {
			jta.append(ex.toString() + '\n');
		}
	}

	public void sendMsg(String msg) {
		try {
			socket.write(ByteBuffer.wrap(msg.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String receiveMsg() {
		String msg = null;
		try {
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			StringBuffer buf = new StringBuffer();
			int count = 0;
			// 不一定一次就能读满，连续读,针对大数据量文本
			while ((count = socket.read(buffer)) > 0) {
				buf.append(new String(buffer.array(), 0, count));
			}
			// 有数据
			if (buf.length() > 0) {
				msg = buf.toString();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				// Get the radius from the text field
				double radius = Double.parseDouble(jtf.getText().trim());

				// Send the radius to the server
				
				sendMsg(jtf.getText().trim());

				// Get area from the server
				String area = null;
				while (area == null) {
					area = receiveMsg();
				}

				// Display to the text area
				jta.append("Radius is " + radius +"Area is " + area + '\n');
				
			} catch (Exception ex) {
				System.err.println(ex);
			}
		}
	}
}
