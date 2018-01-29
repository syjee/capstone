import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;

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
	
	public boolean mineBlock(int difficulty) throws NoSuchAlgorithmException{
		String target = new String(new char[difficulty]).replace('\0', '0');
		boolean isMined = false;
		
		try{
			while(!currentHash.substring(0,difficulty).equals(target)){
				nonce ++;
				currentHash = calculateHash();
			}	
			isMined = true;
			System.out.println("Block for index "+index+" Mined!!");
			
		} catch(Exception e){
			System.out.println("Block for index"+index+" Error... ");
		}
		
		return isMined;
	}
	
	public String getCurrentHash(){
		return this.currentHash;
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
