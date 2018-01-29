import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD {

	private static org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String temp;
		
		
		while(true){
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Message input = ");
			
			if((temp = in.readLine()).equals("q")) break;
			
			System.out.println("MD = "+MakeMD(temp));
		}
		
		System.out.println("quit");
	}
	
	public static String MakeMD(String param){
		String result = "";
		try {
			
			/** public static MessageDigest getInstance(String  algorithm)
			 * 지정된 다이제스트 알고리즘을 구현하는 MessageDigest 오브젝트를 작성합니다.
			 */

			/** public byte[] digest(byte[] input)
			 * 지정된 바이트 배열을 사용해 다이제스트에 대해서 최종의 갱신을 실행한 뒤, 다이제스트 계산을 완료 합니다. 
			 * 즉, 이 메소드는 update(input) 메소드를 호출해 input 배열을 update 메소드에 건네준 뒤,digest() 메소드를 호출합니다. 
			 */


			/** public void update(byte[] input)
			 * 지정된 바이트 배열을 사용해 다이제스트를 갱신합니다. 
			 */

			
			/** public byte[]digest()
			 * 패딩 등의 최종 처리를 행해 해시 계산을 완료 합니다. 이 호출의 그리고, 다이제스트는 리셋트 됩니다. 
			 */


			byte[] digest = MessageDigest.getInstance("MD5").digest(param.getBytes());
			result = base64.encodeAsString(digest);
		
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
		
	}

}
