
import java.io.Serializable;

//Serializable Á÷·ÄÈ­

public class Block implements Serializable{
	
	public String transactoin;
	public String currentHash;
	
	public Block(){
		this.transactoin = "Hello";
		this.currentHash = "abcdefg";
	}
	
	public void setTransaction(String transaction){
		this.transactoin = transaction;
	}
	
	public String getTransaction(){
		return this.transactoin;
	}
	
	public String getCurrentHash(){
		return this.currentHash;
	}

}
