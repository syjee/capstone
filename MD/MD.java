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
			 * ������ ��������Ʈ �˰����� �����ϴ� MessageDigest ������Ʈ�� �ۼ��մϴ�.
			 */

			/** public byte[] digest(byte[] input)
			 * ������ ����Ʈ �迭�� ����� ��������Ʈ�� ���ؼ� ������ ������ ������ ��, ��������Ʈ ����� �Ϸ� �մϴ�. 
			 * ��, �� �޼ҵ�� update(input) �޼ҵ带 ȣ���� input �迭�� update �޼ҵ忡 �ǳ��� ��,digest() �޼ҵ带 ȣ���մϴ�. 
			 */


			/** public void update(byte[] input)
			 * ������ ����Ʈ �迭�� ����� ��������Ʈ�� �����մϴ�. 
			 */

			
			/** public byte[]digest()
			 * �е� ���� ���� ó���� ���� �ؽ� ����� �Ϸ� �մϴ�. �� ȣ���� �׸���, ��������Ʈ�� ����Ʈ �˴ϴ�. 
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
