import java.io.*;

public class buffer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			String name;
			System.out.print("�̸��� �Է��ϼ���:");
			name = in.readLine();
			
			System.out.println("����� �̸��� ("+name+")�Դϴ�.");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
