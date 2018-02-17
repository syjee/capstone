import java.io.File;

public class IpScanManager extends Thread{
	public int threadCounter;
	public threadPool pool;
	public int term;
	public boolean shouldRun = true;
	
	public IpScanManager(){
		pool = new threadPool();
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
					pool.execute(new IpScan(threadCounter));
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
		
		pool.shutdown();
	}
	
	public void updateIpList(){
		File dta = new File("IpList.lst");
		if(dta.exists()) dta.delete();
	}
	
}
