import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

//Serializable 직렬화

/*
public class Block implements Serializable{
	private static final long serialVersionUID = 1L;
	
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


*/

public class Block implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public int index;
	public ArrayList<Transaction> transactions;
	public String currentHash = "aaaaa";
	public String previousHash;
	public int nonce;
	public String transaction;
	public int difficulty;
	public String merkleRoot;
	
	public Block(){}

	public Block(int index,String transaction, String previousHash, String currentHash) {
		this.transactions = new ArrayList<Transaction>();
		
		this.index = index;
		this.transaction = transaction;
		this.previousHash = previousHash;
		this.currentHash = currentHash;
	}
	
	public Block(int index,Transaction transaction, String previousHash, String currentHash,int nonce) {
		this.transactions = new ArrayList<Transaction>();
		
		this.index = index;
		this.transactions.add(transaction);
		this.previousHash = previousHash;
		this.currentHash = currentHash;
		this.nonce = nonce;
	}
	
	
	public Block(ArrayList<Transaction> transactions, String previousHash,String merkleRoot){
		this.transactions = transactions;		
		this.previousHash = previousHash;
		this.merkleRoot = merkleRoot;
	}
	
	public Block(int index, ArrayList<Transaction> transactions, String previousHash,String merkleRoot){
		this.index = index;
		this.transactions = transactions;		
		this.previousHash = previousHash;
		this.merkleRoot = merkleRoot;
	}
	
	public Block(ArrayList<Transaction> transactions){
		this.transactions = transactions;		
	}
	
	public Block(ArrayList<Transaction> transactions,String merkleRoot){
		this.transactions = transactions;		
		this.merkleRoot = merkleRoot;
	}
	
	public Block(int index, ArrayList<Transaction> transactions,String merkleRoot){
		this.index = index;
		this.transactions = transactions;		
		this.merkleRoot = merkleRoot;
	}
	
	public Block(Transaction transaction, String previousHash) {
		this.transactions = new ArrayList<Transaction>();
		this.transactions.add(transaction);
		
		this.previousHash = previousHash;
	}
	
	
	public Block(Transaction transaction) {
		this.transactions = new ArrayList<Transaction>();
		this.transactions.add(transaction);
	}
	
	public void setIndex(int index) {
		this.index = index;
	}

	public void setDifficulty(int difficulty){
		this.difficulty = difficulty;
	}
	public void setCurrentHash(String currentHash) {
		this.currentHash = currentHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public void setTransaction(Transaction transaction){
		this.transactions.add(transaction);
	}
	
	public ArrayList<Transaction> getArrayTransaction(){
		return this.transactions;
	}
	
	public void setMerkleRoot(String merkleRoot){
		this.merkleRoot = merkleRoot;
	}
	
	public String getMerkleRoot(){
		return this.merkleRoot;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public Transaction getTransaction(){
		return this.transactions.get(transactions.size()-1);
	}
	
	public String getStringTransaction(){
		return this.transactions.get(transactions.size()-1).getTransaction();
	}
	
	public String getCurrentHash(){
		return this.currentHash;
	}
	
	public int getDifficulty(){
		return this.difficulty;
	}
	public String getPreviousHash(){
		return this.previousHash;
	}
	
	public String getRawBlock(){
		String rawBlock = index +":";
		rawBlock += transactions.get(1).getTransaction();
		/*
		for(int i = 0; i<transactions.size(); i++){
			rawBlock += transactions.get(i).getTransaction();
		}
		*/
		rawBlock += ":"+ previousHash +":"+ currentHash;
		return rawBlock;
	}
	public String calculateHash() throws NoSuchAlgorithmException {
		String calculatedhash;
 		if(previousHash != null) {
 			calculatedhash = previousHash;
 			for(int i = 0; i<transactions.size(); i++){
 				calculatedhash += transactions.get(i).getTransaction();
 			}
 					  
 			calculatedhash += Integer.toString(nonce);
 		}
 		else {
 			calculatedhash = "";
 			for(int i = 0; i<transactions.size(); i++){
 				calculatedhash += transactions.get(i).getTransaction();
 			}
 					  
 			calculatedhash += Integer.toString(nonce);
 		}
 		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(calculatedhash.getBytes(StandardCharsets.UTF_8));
		String encoded = Base64.getEncoder().encodeToString(hash);

 		return encoded; 
 	} 
	 	 
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
	 	
	 	public void addTransaction(Transaction t){
	 		this.transactions.add(t);
	 	}

}