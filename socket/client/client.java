import java.net.*;
import java.io.*;

public class client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			//Ŭ�� ���α׷��� ������ ������ IP�ּҿ� ��Ʈ��ȣ�� ������ ���� �����Ͽ� ������ ���� ��û
			//���� Socket�� �ϳ��� ��Ʈ�� �����Ͽ� ��밡�� but ServerSocket�� ��Ʈ�� ����
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
