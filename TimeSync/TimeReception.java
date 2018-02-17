package test;

import java.net.InetAddress;
import java.util.Random;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

public class TimeReception extends Thread implements TimeServer{
	
	public String timeServer=null;
	public boolean shouldRun;
	public NTPUDPClient client;
	public TimeSyncronization ts;
	public int term;
	
	public TimeReception() {
		// TODO Auto-generated constructor stub
		this.shouldRun = true;
		this.client = new NTPUDPClient();
		this.ts = new TimeSyncronization();
		this.term = 10000;
	}
	
	public void setTimeServer(){
		Random rand = new Random();
		
		switch(rand.nextInt(5)+1){
		case 1:
			this.timeServer = server1;
			break;
		case 2:
			this.timeServer = server2;
			break;
		case 3:
			this.timeServer = server3;
			break;
		case 4:
			this.timeServer = server4;
			break;
		case 5:
			this.timeServer = server5;
			break;
			}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(shouldRun){
			try {
				setTimeServer();
				client.open();
				
				InetAddress hostAddr = InetAddress.getByName(timeServer);
				TimeInfo info = client.getTime(hostAddr);
				info.computeDetails();
				Long offsetValue = info.getOffset();
				
				ts.timeSync((System.currentTimeMillis() + offsetValue));
				System.out.println("clock_offset(ms) = " + offsetValue);
				System.out.println("get Time from ( " + timeServer+" )");
				
				
				Thread.sleep(term);
				client.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
}
