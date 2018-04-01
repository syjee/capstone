
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

//Serializable 직렬화

public class Block implements Serializable{
	
	public int index;
	public String transactoin;
	public String currentHash = "aaaaa";
	public String previousHash;
	public int nonce;
	
	public Block(){}

	public Block(int index,String transaction, String previousHash, String currentHash) {
		this.index = index;
		this.transactoin = transaction;
		this.previousHash = previousHash;
		this.currentHash = currentHash;
	}
	
	public Block(String transaction, String previousHash) {
		this.transactoin = transaction;
		this.previousHash = previousHash;
	}
	
	public Block(String transaction) {
		this.transactoin = transaction;
	}
	
	public Block(int index,String transaction, String previousHash, String currentHash,int nonce) {
		this.index = index;
		this.transactoin = transaction;
		this.previousHash = previousHash;
		this.currentHash = currentHash;
		this.nonce = nonce;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}

	public void setCurrentHash(String currentHash) {
		this.currentHash = currentHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public void setTransaction(String transaction){
		this.transactoin = transaction;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public String getTransaction(){
		return this.transactoin;
	}
	
	public String getCurrentHash(){
		return this.currentHash;
	}
	
	public String getPreviousHash(){
		return this.previousHash;
	}
	
	public String getRawBlock(){
		String rawBlock = index +":"+ transactoin +":"+ previousHash +":"+ currentHash;
		return rawBlock;
	}

	/** SHA-256 암호화 
	 	 * 현재 블록의 순서번호, 현재 시간, 트랜잭션, nonce, 이전 블록의 해쉬값을 암호화한 값이 현재 블록의 해쉬값이 된다. 
	 	 * */ 
/*
	
		public String calculateHash() throws NoSuchAlgorithmException {
	 		MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
			String encoded = Base64.getEncoder().encodeToString(hash);
			
	 		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256"); 
	 		String calculatedhash;
	 		if(previousHash != null) {
	 			calculatedhash = previousHash +  
	 					transactoin +  
	 					Integer.toString(nonce);
	 		}
	 		else {
	 			calculatedhash =  transactoin +  
	 					Integer.toString(nonce);
	 		}
	 		 
	 		byte[] temp = messageDigest.digest(calculatedhash.getBytes(StandardCharsets.UTF_8)); 
	 		temp = messageDigest.digest(temp); 
	 
	 
	 		return new String(temp,0,temp.length); 
	 	} 
	 	
*/
	
	public String calculateHash() throws NoSuchAlgorithmException {
		String calculatedhash;
 		if(previousHash != null) {
 			calculatedhash = previousHash +  
 					transactoin +  
 					Integer.toString(nonce);
 		}
 		else {
 			calculatedhash =  transactoin +  
 					Integer.toString(nonce);
 		}
 		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(calculatedhash.getBytes(StandardCharsets.UTF_8));
		String encoded = Base64.getEncoder().encodeToString(hash);

 		return encoded; 
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
	 				setCurrentHash(calculateHash());
	 			}	 
	 			System.out.println("[MINE]Mining Success!!! nonce = " + (nonce-1));
	 			isMined = true; 
	 			 
	 		} catch(Exception e){ 
	 			System.out.println("mineBlock() Error... "); 
	 		} 
	 		 
	 		return isMined; 
	 	}


}
