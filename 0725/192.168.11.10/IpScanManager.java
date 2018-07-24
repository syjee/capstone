import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class IpScanManager extends Thread{
	public int threadCounter;
	public ThreadPoolExecutor tpx;
	public int term;
	public boolean shouldRun = true;
	public DBHelper db;
	
	public String subnet = "192.168.11";
	
	public IpScanManager(){
		tpx = new ThreadPoolExecutor(50,100,100L,TimeUnit.SECONDS, new LinkedBlockingQueue());
		term = 600000;
		db = new DBHelper();
	}
	
	public IpScanManager(String subnet){
		tpx = new ThreadPoolExecutor(50,100,100L,TimeUnit.SECONDS, new LinkedBlockingQueue());
		term = 600000;
		db = new DBHelper();
		this.subnet = subnet;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(shouldRun){
			try {
				threadCounter = 1;
				db.updateFile(db.getIpListFile());
				
				while (true) {
					tpx.execute(new IpScan(subnet,threadCounter));
					threadCounter++;
					if (threadCounter == 255)
						break;
					}
				Thread.sleep(term);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("IpScanManager Thread running...");
		}
		
		tpx.shutdown();
	}	
}
