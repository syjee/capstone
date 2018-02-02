import java.security.NoSuchAlgorithmException;
import java.util.*;

/** @author 소영
 * 블록체인의 마이닝을 간단히 구현.
 * BlockGeneratorManager가 BlockGenerator를 관리한다.
 * BlockGenerator들은 마이닝을 수행하는 채굴자로 Block을 생성한다. 
 * BlockGenerator 스레드들 중 가장 빨리 마이닝한 블록을 블록체인에 추가한다.
 * 다음은 블록 2개를 생성하는 예시.
 */

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//제네시스 블록 생성 과정
		BlockGeneratorManager BGM = new BlockGeneratorManager();
		BlockGeneratorManager BGM2 = new BlockGeneratorManager();
		Blockchain block_chain = new Blockchain();
	
		
		BGM.start();
		BGM.putRequest(block_chain.getHeight()+1,null, "Do something");
		BGM.join();
		if(BGM.getBlock()!=null)
			block_chain.addBlock(BGM.getBlock());
		

		BGM2.start();
		BGM2.putRequest(block_chain.getHeight()+1,block_chain.getBlock(), "A gave 10bitcoin to B");
		BGM2.join();
		if(BGM2.getBlock()!=null)
			block_chain.addBlock(BGM2.getBlock());
		
		
		block_chain.showBlock();
		
	}

}
