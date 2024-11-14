package net;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
public class DatagramTest2 {

	public static void main(String [] arg) throws IOException, ClassNotFoundException {
		DatagramSocket ds =new DatagramSocket(9000);
		byte[] s=new byte[256];
		DatagramPacket dp=new DatagramPacket(s, s.length);		
		ds.receive(dp);
		InetAddress add=dp.getAddress();
		int port=dp.getPort();
		System.out.println(port);
		String str=new String(dp.getData());
		System.out.println(str);
		BaseUser bu=new BaseUser();
		bu.BaseInt=20;
		ByteArrayOutputStream bo=new ByteArrayOutputStream();
		ObjectOutputStream oo=new ObjectOutputStream(bo);
		oo.writeObject(bu);
		s=bo.toByteArray();
		dp=new DatagramPacket(s, s.length,add,port);
		ds.send(dp);
		
		
		
	}
}
