import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;

/** index - 블록 순서
 * currentHash - 현재 생성할 블록의 해쉬값
 * previousHash - 이전 블록의 해쉬값
 * data - 트랜잭션
 * timeStamp - 블록 생성 시간
 * nonce - 마이닝 성공시킨 정수값
 * */

public class Block{
	public int index;
	public String currentHash;
	public String previousHash;
	private String data;
	private long timeStamp;
	private int nonce;
	
	public Block(int index, String data, String previousHash) throws NoSuchAlgorithmException{
		this.index = index;
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.currentHash = calculateHash();
	}
	
	/** SHA-256 암호화
	 * 현재 블록의 순서번호, 현재 시간, 트랜잭션, nonce, 이전 블록의 해쉬값을 암호화한 값이 현재 블록의 해쉬값이 된다.
	 * */
	
	public String calculateHash() throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		
		String calculatedhash = Integer.toString(index) + previousHash +
				Long.toString(timeStamp) +
				data + 
				Integer.toString(nonce);
		
		byte[] temp = messageDigest.digest(calculatedhash.getBytes(StandardCharsets.UTF_8));
		temp = messageDigest.digest(temp);

		return new String(temp,0,temp.length);
	}
	
	/** 블록 마이닝
	 * 매개변수 difficulty 값에 따라 마이닝 타겟이 정해진다. ex) difficulty=4, target = 0000...
	 */
	
	public boolean mineBlock(int difficulty) throws NoSuchAlgorithmException{
		String target = new String(new char[difficulty]).replace('\0', '0');
		boolean isMined = false;
		
		try{
			while(!currentHash.substring(0,difficulty).equals(target)){
				nonce ++;
				currentHash = calculateHash();
			}	
			isMined = true;
			
		} catch(Exception e){
			System.out.println("mineBlock() Error... ");
		}
		
		return isMined;
	}
	
	public String getCurrentHash(){
		return this.currentHash;
	}
	
	public int getIndex(){
		return this.index;
	}
	
	public void showBlock(){
		System.out.println("**********************Block"+index+"*********************");
		System.out.println("index = "+this.index);
		System.out.println("time = "+ this.timeStamp);
		System.out.println("data = "+this.data);
		System.out.println("nonce = "+this.nonce);
		System.out.println("previousHash = "+this.previousHash);
		System.out.println("currentHash = "+this.currentHash);
	}
		
}
