import java.io.Serializable;

public class Transaction implements Serializable{

	public String transaction=null;
	public int difficulty = 2;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
		this.transaction = String.valueOf(System.currentTimeMillis());
	}
	
	public Transaction(int difficulty) {
		// TODO Auto-generated constructor stub
		this.difficulty = difficulty;
		this.transaction = String.valueOf(System.currentTimeMillis());
	}

	public void setTransaction() {
		this.transaction = String.valueOf(System.currentTimeMillis());
	}
	
	public String getTransaction() {
		return this.transaction;
	}
	
	public int getDifficulty() {
		return this.difficulty;
	}
	
}
