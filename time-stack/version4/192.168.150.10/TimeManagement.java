import java.awt.RenderingHints.Key;
import java.util.ArrayList;

public class TimeManagement {

	public KeyManager km;
	public ArrayList<Time> mTime;

	public TimeManagement(KeyManager km) {
		this.km = km;
		this.mTime = new ArrayList<Time>();
	}

	public void addTransaction(Transaction transaction, String sourceHost) {
		String transactionHash = transaction.getTransaction();
		String time = km.decrypt(transaction.getTransaction(), km.getPublicKey(sourceHost));

		mTime.add(new Time(transactionHash, time, sourceHost));
		mTime.get(mTime.size() - 1).getStopWatch().start();
	}

	public void compare(Block block) {
		String targetHash;
		for (int k = 0; k < block.getArrayTransaction().size(); k++) {
			targetHash = block.getArrayTransaction().get(k).getTransaction(); 
			
			for (int i = 0; i < mTime.size(); i++) {
				if (mTime.get(i).getTransactionHash().equals(targetHash)) {
					mTime.get(i).getStopWatch().pause();
					mTime.remove(i);
				}
			}
		}
	}
	
}
