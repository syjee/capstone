import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class IpScanManager extends Thread{
	public int threadCounter;
	public ThreadPoolExecutor tpx;
	public int term;
	public boolean shouldRun = true;
	
	public IpScanManager(){
		tpx = new ThreadPoolExecutor(50,100,100L,TimeUnit.SECONDS, new LinkedBlockingQueue<>());
		term = 600000;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(shouldRun){
			try {
				threadCounter = 0;
				updateIpList();
				
				while (true) {
					threadCounter++;
					tpx.execute(new IpScan(threadCounter));
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
	
	public void updateIpList(){
		File dta = new File("IpList.lst");
		if(dta.exists()) dta.delete();
	}
	
}
