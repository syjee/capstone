import java.io.IOException;
import java.io.*;
import java.net.*;

/** *서버 소켓은 연결만 처리, 실질적인 데이터는 소켓끼리 서로 주고 받습니당
** 입출력 스트림을 통해서.. 여기서는 바이트 스트림
** 바이트 스트림 - 데이터의 종류가 파일, 그림, 동영상 등 바이트 기반인 경우 사용하는 클래스로 바이트 단위로 입출력을 제어합니당.
** InputStram, OutputStream 입출력을 위한 바이트 스트림의 최상위 추상 클래스
*/

public class server {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			//서버 소켓 생성, 특정 포트에서 클라 연결을 기다립니당
			ServerSocket s_socket = new ServerSocket(8016);
			
			//클라 연결 요청을 받으면 새로운 소켓을 생성하여 클라의 소켓과 연결
			Socket c_socket = s_socket.accept();
			
			
			OutputStream output_data = c_socket.getOutputStream();
			
			String sendDataString = "Welcome to My Server";
			output_data.write(sendDataString.getBytes());
			
			s_socket.close();
			c_socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
