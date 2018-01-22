import java.io.*;

public class buffer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			String name;
			System.out.print("이름을 입력하세요:");
			name = in.readLine();
			
			System.out.println("당신의 이름은 ("+name+")입니다.");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
