package test;

import java.io.*;
import java.net.*;


public class server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ServerSocket s_socket;
		Socket c_socket;
		try {
			s_socket = new ServerSocket(8015);
			c_socket = s_socket.accept();
			
			OutputStream os = c_socket.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			
			Block b = new Block();
			b.setTransaction("Request something...");
			
			oos.writeObject(b);
			
			oos.flush();
			oos.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
