package test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		try {
			Socket socket = new Socket("192.168.11.106",8015);
			
			InputStream is = socket.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			Block b = (Block)ois.readObject();
	
			System.out.println(b.getTransaction());
			ois.close();
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
