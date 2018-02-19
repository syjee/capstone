public class Transaction {

	public String transaction;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
		this.transaction = String.valueOf(System.currentTimeMillis());
	}
	
	public void setTransaction() {
		this.transaction = String.valueOf(System.currentTimeMillis());
	}
	
	public String getTransation() {
		return this.transaction;
	}
}
