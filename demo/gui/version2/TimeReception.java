
import java.net.InetAddress;
import java.util.*;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

public class TimeReception implements TimeServer{
	
	public String timeServer=null;
	public NTPUDPClient client;
	public TimeSyncronization ts;
	public Long offsetValue;
	public long msNewTime;
	
	public TimeReception() {
		// TODO Auto-generated constructor stub
		this.client = new NTPUDPClient();
		this.ts = new TimeSyncronization();
	}
	
	public void initTimeServer(){
	//	Random rand = new Random();
		
		Long time;
		String tempServer = "";
		
		
		this.timeServer = server1;
		this.timereceive();
		tempServer = timeServer;
		
		time = offsetValue;
		
		this.timeServer = server2;
		this.timereceive();
		if(Math.abs(time) > Math.abs(offsetValue)) {
			time = offsetValue;
			tempServer = timeServer;
		}
		
		this.timeServer = server3;
		this.timereceive();
		if(Math.abs(time) > Math.abs(offsetValue)) {
			time = offsetValue;
			tempServer = timeServer;
		}
		
		this.timeServer = server4;
		this.timereceive();
		if(Math.abs(time) > Math.abs(offsetValue)) {
			time = offsetValue;
			tempServer = timeServer;
		}
		
		this.timeServer = server5;
		this.timereceive();
		if(Math.abs(time) > Math.abs(offsetValue)) {
			time = offsetValue;
			tempServer = timeServer;
		}
		
		this.timeServer = tempServer;
		/*
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
			}*/
		System.out.println("[TIMESERVER]"+timeServer);
		System.out.println("[OFFSET]"+offsetValue);
	}
	
	public void setTimeServer(String server){
		this.timeServer = server;
	}
	
	public String getTimeServer(){
		return this.timeServer;
	}
	
	public void timereceive() {
		try {
		//	setTimeServer();
			client.open();
				
			InetAddress hostAddr = InetAddress.getByName(timeServer);
			TimeInfo info = client.getTime(hostAddr);
			info.computeDetails();
			offsetValue = info.getOffset();
			
			System.out.println("[TIMESERVER]"+timeServer);
			System.out.println("[OFFSET]"+offsetValue);
			
			//ts.timeSync((System.currentTimeMillis() + offsetValue));
//			System.out.println("clock_offset(ms) = " + offsetValue);
//			System.out.println("get Time from ( " + timeServer+" )");//+" : "+time
			
			client.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
	
	public void timesynchronize() {
		try {
			String strValueOffset = offsetValue.toString();
			long longValueOffset = Long.parseLong(strValueOffset);
			msNewTime = System.currentTimeMillis() + longValueOffset;
			ts.timeSync((msNewTime));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
		}
	}
}

/*public class TimeReception extends Thread implements TimeServer{
	
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
		this.term = 600000;
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
				offsetValue = info.getOffset();
				
				ts.timeSync((System.currentTimeMillis() + offsetValue));
				System.out.println("clock_offset(ms) = " + offsetValue);
				System.out.println("get Time from ( " + timeServer+" )");
				
				Thread.sleep(term);
				//client.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}*/