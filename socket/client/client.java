import java.net.*;
import java.io.*;

public class client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			//클라 프로그램은 접속하 서버의 IP주소와 포트번호를 가지고 소켓 생성하여 서버에 연결 요청
			//여러 Socket이 하나의 포트를 공유하여 사용가능 but ServerSocket은 포트를 독점
			Socket c_socket = new Socket("172.30.120.69",8016);
		
			InputStream input_data = c_socket.getInputStream();
			
			byte[] receiveBuffer = new byte[100];
			input_data.read(receiveBuffer);
			
			System.out.println(new String(receiveBuffer));
			
			c_socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
