import java.io.IOException;
import java.net.*;

public class main_server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			ServerSocket s_socket = new ServerSocket(8015);
			
			Socket c_socket = s_socket.accept();
			OutputThread t1 = new OutputThread(c_socket);
			
			t1.start();
			
			t1.write("Hello");
			t1.write("World");

			t1.join();
			
			s_socket.close();
			c_socket.close();
		
			System.out.println("socket close");
			
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
