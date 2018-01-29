import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class BlockHeader {
	
	private int version;	//���� : ����Ʈ����, �Ǵ� �������� ���� ���׷��̵带 �����ϱ� ���� ���Ǵ� ���� ����
	private byte[] previousBlockHash;	//���� ����� �ؽ� : ���ü�� ���� ���� ���(�θ� ���)�� �ؽð�
	private int merkleRootHash;	//��Ŭ ��Ʈ�� �ؽ� : ��ŬƮ���� ��Ʈ�� ���� �ؽð�
	private int timestamp;	//Ÿ�ӽ����� : �ش� ����� ���� �ð�
	private int difficulty;	//���̵� ��ǥ : ä���������� �ʿ��� �۾� ����(Proof of Work) �˰����� ���̵� ��ǥ
	private int nonce;	//���� : ä�������� �۾� ������ ���Ǵ� ī����
	
	public BlockHeader(byte[] previousBlockHash, Object[] transactions){
		this.previousBlockHash = previousBlockHash;
		this.merkleRootHash = this.someMethod(transactions);
	}
	
	public byte[] toByteArray(){
		String tmpStr = "";
		if(previousBlockHash != null){
			tmpStr += new String(previousBlockHash, 0, previousBlockHash.length);
		}
		tmpStr += merkleRootHash;
		return tmpStr.getBytes(StandardCharsets.UTF_8);
	}

	/** hashCode(Object[] o)
	 * ���޵� �迭 ���ڿ� �ٰ��ϴ� �ؽ��ڵ带 ��ȯ.
	 */
	private int someMethod(Object[] transactions){
		return Arrays.hashCode(transactions);
	}
}
