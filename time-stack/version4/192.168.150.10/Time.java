
public class Time {
	String transactionHash;
	String time;
	String sourceHost;
	StopWatch stopWatch;

	public Time(String transactionHash, String time, String sourceHost) {
		this.transactionHash = transactionHash;
		this.time = time;
		this.sourceHost = sourceHost;
		this.stopWatch = new StopWatch();
		stopWatch.start();
	}

	public StopWatch getStopWatch() {
		return this.stopWatch;
	}
	
	public String getTransactionHash() {
		return this.transactionHash;
	}
}
