import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class main_client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Socket c_socket = new Socket("172.30.120.69",8015);
			
			InputThread t1 = new InputThread(c_socket);
			
			t1.start();			
			t1.join();
			
			c_socket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
