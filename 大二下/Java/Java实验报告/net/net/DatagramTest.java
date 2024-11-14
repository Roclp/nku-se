package net;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
public class DatagramTest {

	public static void main(String [] arg) throws IOException, ClassNotFoundException {
		DatagramSocket ds =new DatagramSocket();
		byte[] s="hello".getBytes();
		DatagramPacket dp=new DatagramPacket(s, s.length, InetAddress.getByName("127.0.0.1"), 9000);
		ds.send(dp);
		s=new byte[256];
		dp=new DatagramPacket(s, s.length);
		ds.receive(dp);
		s=dp.getData();
		ByteArrayInputStream bi=new ByteArrayInputStream(dp.getData());
		ObjectInputStream oi=new ObjectInputStream(bi);
		BaseUser bu=(BaseUser) oi.readObject();
		System.out.println(bu);
		
		
	}
}
