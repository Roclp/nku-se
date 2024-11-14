package net;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.InputStreamReader;
public class URLTest {

	public static void main(String[] arg) throws IOException {
		URL us=new URL("https://www.nankai.edu.cn/");
		URLConnection uc=us.openConnection();;
		
		InputStream is=uc.getInputStream();
		BufferedReader r=new BufferedReader(new InputStreamReader(is,"utf-8"));
		//(is);
		//while(ds.)
		while(true) {
			String s;
			try {
				s=r.readLine();
				if(null==s||s.equals("null")) {
					break;
				}
				System.out.println(s);
		
			}
			catch (Exception e){
				
			}
		}
	}
}
