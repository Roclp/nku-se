import java.io.*;
import java.util.zip.*;

public class GZIPCompress {
  public static void main ( String [] args ) 
  throws IOException {
    BufferedReader in = 
	    new BufferedReader (
		    new FileReader( args[0] ) );
	
	BufferedOutputStream out =
        new BufferedOutputStream (
		    new GZIPOutputStream (
			    new FileOutputStream(args[1] ) ));

	System.out.println("Compressing file...");
	int c;
	while ( (c = in.read()) != -1 )
	    out.write(c);
	in.close();
	out.close();

	System.out.println("Verifying file...");
	BufferedReader in2 =
	    new BufferedReader (
		    new InputStreamReader (
			    new GZIPInputStream (
				    new FileInputStream( args[1] ) )));
	String s;
	while ( (s = in2.readLine())  != null )
		System.out.println(s);
  }
}