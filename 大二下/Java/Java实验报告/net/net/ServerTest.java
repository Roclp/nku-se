package net;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

	public static void main(String [] arg) throws IOException, InterruptedException {
		ServerSocket ss=new ServerSocket(1111);
		Socket socket=ss.accept();
		System.out.println("connect");
		InputStream is=socket.getInputStream();
		DataInputStream di=new DataInputStream(is);
		System.out.println(di.readUTF());
		
		ObjectInputStream oi=new ObjectInputStream((socket.getInputStream()));
		BaseUser bu=null;
		try {
			 bu=(BaseUser)oi.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(bu);
		ss.close();
		
		
	}
}
