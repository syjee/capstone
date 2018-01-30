import java.security.NoSuchAlgorithmException;
import java.util.*;

/** @author 소영
 * 블록체인의 마이닝을 간단히 구현.
 * BlockGeneratorManager가 BlockGenerator를 관리한다.
 * BlockGenerator들은 마이닝을 수행하는 채굴자로 Block을 생성한다. 
 */

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//제네시스 블록 생성 과정
		BlockGeneratorManager BGM = new BlockGeneratorManager();
		BGM.putRequest(null, "Do something");
		
		
	}

}
