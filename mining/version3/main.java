import java.security.NoSuchAlgorithmException;
import java.util.*;

/** @author �ҿ�
 * ���ü���� ���̴��� ������ ����.
 * BlockGeneratorManager�� BlockGenerator�� �����Ѵ�.
 * BlockGenerator���� ���̴��� �����ϴ� ä���ڷ� Block�� �����Ѵ�. 
 * BlockGenerator ������� �� ���� ���� ���̴��� ����� ���ü�ο� �߰��Ѵ�.
 * ������ ��� 2���� �����ϴ� ����.
 */

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//���׽ý� ��� ���� ����
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
