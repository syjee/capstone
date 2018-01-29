import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class BlockHeader {
	
	private int version;	//버전 : 소프트웨어, 또는 프로토콜 등의 업그레이드를 추적하기 위해 사용되는 버전 정보
	private byte[] previousBlockHash;	//이전 블록의 해시 : 블록체인 상의 이전 블록(부모 블록)의 해시값
	private int merkleRootHash;	//머클 루트의 해시 : 머클트리의 루트에 대한 해시값
	private int timestamp;	//타임스탬프 : 해당 블록의 생성 시각
	private int difficulty;	//난이도 목표 : 채굴과정에서 필요한 작업 증명(Proof of Work) 알고리즘의 난이도 목표
	private int nonce;	//난스 : 채굴과정의 작업 증명에서 사용되는 카운터
	
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
	 * 전달된 배열 인자에 근거하는 해시코드를 반환.
	 */
	private int someMethod(Object[] transactions){
		return Arrays.hashCode(transactions);
	}
}
