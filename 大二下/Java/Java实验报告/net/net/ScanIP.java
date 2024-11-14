package net;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ScanIP {

	public static void main(String[] str) throws Exception {
		byte[] ip= {(byte) 192,(byte) 168,10,1};
		InetAddress add=InetAddress.getByAddress(ip);
		for(byte i=1;i<255;i++) {
			ip[3]++;
			add=InetAddress.getByAddress(ip);
			try {
			Socket s=new Socket(add,8000);
			System.out.println(add.toString());
			}
			catch(Exception e) {
				
			}
			
		}
		
		
	}
}
