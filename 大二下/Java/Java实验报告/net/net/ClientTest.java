package net;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		BaseUser bu=new BaseUser();
		bu.BaseInt=10;
		//Socket cs=new Socket("127.0.0.1",1111);
		Socket cs=new Socket(InetAddress.getLocalHost(),1111);
		DataOutputStream dos=new DataOutputStream(cs.getOutputStream());
		dos.writeUTF("ÄãºÃ");
		ObjectOutputStream oo=new ObjectOutputStream(new BufferedOutputStream(cs.getOutputStream()));
		oo.writeObject(bu);
		oo.flush();
		//Thread.sleep(100);
		cs.close();
		
		
				
		
		

	}

}
