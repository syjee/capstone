
import java.io.Serializable;

//Serializable Á÷·ÄÈ­

public class Block implements Serializable{
	
	public int index;
	public String transactoin;
	public String currentHash;
	public String previousHash;
	
	public Block(){}

	public Block(int index,String transacation, String previousHash, String currentHash) {
		this.index = index;
		this.transactoin = transacation;
		this.previousHash = previousHash;
		this.currentHash = currentHash;
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

}
