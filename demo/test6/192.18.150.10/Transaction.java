import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public String transaction=null;
	public int difficulty = 2;
	public KeyManager km;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
		this.transaction = String.valueOf(System.currentTimeMillis());
	}
	
	public Transaction(int difficulty) {
		// TODO Auto-generated constructor stub
		this.difficulty = difficulty;
		this.transaction = String.valueOf(System.currentTimeMillis());
		
		
	}

	public Transaction(int difficulty,KeyManager km) throws Exception {
		// TODO Auto-generated constructor stub
		this.difficulty = difficulty;
		this.km= km;
		Calendar cal;
		SimpleDateFormat form;
		
		cal = Calendar.getInstance();
		form = new SimpleDateFormat("hh:mm:ss");

		long time = System.currentTimeMillis();
		cal.setTimeInMillis(time);
		String str = form.format(cal.getTime());
		
		System.out.println(str+"!!!!!");
		
		this.transaction = km.encrypt(String.valueOf(time), km.getPrivateKey());
		
		
	}
	
	public Transaction(int difficulty,KeyManager km, String destination) throws Exception {
		// TODO Auto-generated constructor stub
		this.difficulty = difficulty;
		this.km= km;
		this.transaction = km.encrypt(String.valueOf(System.currentTimeMillis()), km.getPrivateKey());
		this.transaction = km.encrypt(this.transaction, km.getPublicKey(destination));
		
		Calendar cal;
		SimpleDateFormat form;
		
		cal = Calendar.getInstance();
		form = new SimpleDateFormat("hh:mm:ss");

		long time = System.currentTimeMillis();
		cal.setTimeInMillis(time);
		String str = form.format(cal.getTime());
		
		System.out.println(str+"!!!!!");
	}
	
	public void setTransaction() throws Exception {
		this.transaction = km.encrypt(String.valueOf(System.currentTimeMillis()), km.getPrivateKey());
	}
	
	public void setTransaction(String str){
		this.transaction = str;
	}
	
	public String getTransaction() {
		return this.transaction;
	}
	
	public int getDifficulty() {
		return this.difficulty;
	}
	
}
