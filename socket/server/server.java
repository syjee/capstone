import java.io.IOException;
import java.io.*;
import java.net.*;

/** *���� ������ ���Ḹ ó��, �������� �����ʹ� ���ϳ��� ���� �ְ� �޽��ϴ�
** ����� ��Ʈ���� ���ؼ�.. ���⼭�� ����Ʈ ��Ʈ��
** ����Ʈ ��Ʈ�� - �������� ������ ����, �׸�, ������ �� ����Ʈ ����� ��� ����ϴ� Ŭ������ ����Ʈ ������ ������� �����մϴ�.
** InputStram, OutputStream ������� ���� ����Ʈ ��Ʈ���� �ֻ��� �߻� Ŭ����
*/

public class server {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			//���� ���� ����, Ư�� ��Ʈ���� Ŭ�� ������ ��ٸ��ϴ�
			ServerSocket s_socket = new ServerSocket(8016);
			
			//Ŭ�� ���� ��û�� ������ ���ο� ������ �����Ͽ� Ŭ���� ���ϰ� ����
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
