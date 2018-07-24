
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpUtils;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;

public class TimeReception extends Thread implements TimeServer {

	public String timeServer = null;
	public NTPUDPClient client;
	public TimeSyncronization ts;
	public Long offsetValue;
	public Long msNewTime;
	public InetAddress hostAddr;

	public TimeReception() {
		// TODO Auto-generated constructor stub
		this.client = new NTPUDPClient();
		this.ts = new TimeSyncronization();
	}

	public void setTimeServer(int num) {
		// Random rand = new Random();

		/*
		 * switch(rand.nextInt(5)+1){ case 1: this.timeServer = server1; break; case 2:
		 * this.timeServer = server2; break; case 3: this.timeServer = server3; break;
		 * case 4: this.timeServer = server4; break; case 5: this.timeServer = server5;
		 * break; }
		 */

		switch (num) {
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
		case 6:
			this.timeServer = server6;
			break;
		default:
			this.timeServer = server1;
			break;
		}

	}

	private static final NumberFormat numberFormat = new java.text.DecimalFormat("0.00");
	
	public static void processResponse(TimeInfo info) {
		TimeSyncronization ts = new TimeSyncronization();
		NtpV3Packet message = info.getMessage();
		
		/*
		int stratum = message.getStratum();
		String refType;
		if (stratum <= 0)
			refType = "(Unspecified or Unavailable)";
		else if (stratum == 1)
			refType = "(Primary Reference; e.g., GPS)"; // GPS, radio clock, etc.
		else
			refType = "(Secondary Reference; e.g. via NTP or SNTP)";
		System.out.println(" Stratum: " + stratum + " " + refType);
		int version = message.getVersion();
		int li = message.getLeapIndicator();
		System.out.println(" leap=" + li + ", version=" + version + ", precision=" + message.getPrecision());
		System.out.println(" mode: " + message.getModeName() + " (" + message.getMode() + ")");
		int poll = message.getPoll();
		System.out.println(" poll: " + (poll <= 0 ? 1 : (int) Math.pow(2, poll)) + " seconds" + " (2 ** " + poll + ")");
		double disp = message.getRootDispersionInMillisDouble();
		System.out.println(" rootdelay=" + numberFormat.format(message.getRootDelayInMillisDouble()) + ", rootdispersion(ms): " + numberFormat.format(disp));
		*/
		int refId = message.getReferenceId();
		
		String refAddr = NtpUtils.getHostAddress(refId);
		/*
		String refName = null;
		
		
		if (refId != 0) {
			if (refAddr.equals("127.127.1.0")) {
				refName = "LOCAL";
			} else if (stratum >= 2) {
				if (!refAddr.startsWith("127.127")) {
					try {
						InetAddress addr = InetAddress.getByName(refAddr);
						String name = addr.getHostName();
						if (name != null && !name.equals(refAddr))
							refName = name;
					} catch (UnknownHostException e) {
						refName = NtpUtils.getReferenceClock(message);
					}
				}
			} else if (version >= 3 && (stratum == 0 || stratum == 1)) {
				refName = NtpUtils.getReferenceClock(message);
			}
		}
		if (refName != null && refName.length() > 1)
			refAddr += " (" + refName + ")";
		*/
		
	//	System.out.println(" Reference Identifier:\t" + refAddr);
		TimeStamp refNtpTime = message.getReferenceTimeStamp();
	//	System.out.println(" Reference Timestamp:\t" + refNtpTime + "  " + refNtpTime.toDateString());
		TimeStamp origNtpTime = message.getOriginateTimeStamp();
	//	System.out.println(" Originate Timestamp:\t" + origNtpTime + "  " + origNtpTime.toDateString());
		long destTime = info.getReturnTime();
		TimeStamp rcvNtpTime = message.getReceiveTimeStamp();
	//	System.out.println(" Receive Timestamp:\t" + rcvNtpTime + "  " + rcvNtpTime.toDateString());
		TimeStamp xmitNtpTime = message.getTransmitTimeStamp();
	//	System.out.println(" Transmit Timestamp:\t" + xmitNtpTime + "  " + xmitNtpTime.toDateString());
		TimeStamp destNtpTime = TimeStamp.getNtpTime(destTime);
	//	System.out.println(" Destination Timestamp:\t" + destNtpTime + "  " + destNtpTime.toDateString());

		info.computeDetails(); 
		Long offsetValue = info.getOffset();
		Long delayValue = info.getDelay();
		
		/*
		SimpleDateFormat  form = new SimpleDateFormat("HH:mm:ss.SS");
		Runtime rt = Runtime.getRuntime();
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis() + destNtpTime.getTime() - origNtpTime.getTime() + offsetValue);
		*/
		
		ts.StringTimeSync(rcvNtpTime.toDateString().substring(17, 28));
//		System.out.println(rcvNtpTime.toDateString().substring(17, 28));
		
		
		System.out.println("org : "+origNtpTime.toDateString());
		System.out.println("rcv : "+rcvNtpTime.toDateString());
		System.out.println("xmit : "+xmitNtpTime.toDateString());
		System.out.println("dest : "+destNtpTime.toDateString());
		
		
		/*
		String str = form.format(cal.getTime());
		Process pc = null;
		try {
			pc = rt.exec("cmd /c time "+str.substring(0,11));
			System.out.println(str.substring(0,11));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pc.destroy();
		*/
		/*
		if(info.getOffset() > 0)
			ts.timeSync(System.currentTimeMillis() + info.getOffset());
		else ts.timeSync(System.currentTimeMillis() - info.getOffset());
		*/
		
		/*
		if(delayValue > 0)
			ts.timeSync(refNtpTime.getTime() - offsetValue);
		else ts.timeSync(refNtpTime.getTime() + offsetValue);
		*/
		
		
				
		
		
		
		
//		String delay = (delayValue == null) ? "N/A" : delayValue.toString();
//		String offset = (offsetValue == null) ? "N/A" : offsetValue.toString();
//		System.out.println(" Roundtrip delay(ms)=" + delay
//				+ ", clock offset(ms)=" + offset);
		
	}
	
	public static void processResponse2(TimeInfo info) {
		NtpV3Packet message = info.getMessage();
		/*
		int stratum = message.getStratum();
		String refType;
		if (stratum <= 0)
			refType = "(Unspecified or Unavailable)";
		else if (stratum == 1)
			refType = "(Primary Reference; e.g., GPS)"; // GPS, radio clock, etc.
		else
			refType = "(Secondary Reference; e.g. via NTP or SNTP)";
		System.out.println(" Stratum: " + stratum + " " + refType);
		int version = message.getVersion();
		int li = message.getLeapIndicator();
		System.out.println(" leap=" + li + ", version=" + version + ", precision=" + message.getPrecision());
		System.out.println(" mode: " + message.getModeName() + " (" + message.getMode() + ")");
		int poll = message.getPoll();
		System.out.println(" poll: " + (poll <= 0 ? 1 : (int) Math.pow(2, poll)) + " seconds" + " (2 ** " + poll + ")");
		double disp = message.getRootDispersionInMillisDouble();
		System.out.println(" rootdelay=" + numberFormat.format(message.getRootDelayInMillisDouble()) + ", rootdispersion(ms): " + numberFormat.format(disp));
		*/
		int refId = message.getReferenceId();
		
		String refAddr = NtpUtils.getHostAddress(refId);
		/*
		String refName = null;
		
		
		if (refId != 0) {
			if (refAddr.equals("127.127.1.0")) {
				refName = "LOCAL";
			} else if (stratum >= 2) {
				if (!refAddr.startsWith("127.127")) {
					try {
						InetAddress addr = InetAddress.getByName(refAddr);
						String name = addr.getHostName();
						if (name != null && !name.equals(refAddr))
							refName = name;
					} catch (UnknownHostException e) {
						refName = NtpUtils.getReferenceClock(message);
					}
				}
			} else if (version >= 3 && (stratum == 0 || stratum == 1)) {
				refName = NtpUtils.getReferenceClock(message);
			}
		}
		if (refName != null && refName.length() > 1)
			refAddr += " (" + refName + ")";
		*/
		
//		System.out.println(" Reference Identifier:\t" + refAddr);
		TimeStamp refNtpTime = message.getReferenceTimeStamp();
//		System.out.println(" Reference Timestamp:\t" + refNtpTime + "  " + refNtpTime.toDateString());
		TimeStamp origNtpTime = message.getOriginateTimeStamp();
//		System.out.println(" Originate Timestamp:\t" + origNtpTime + "  " + origNtpTime.toDateString());
		long destTime = info.getReturnTime();
		TimeStamp rcvNtpTime = message.getReceiveTimeStamp();
//		System.out.println(" Receive Timestamp:\t" + rcvNtpTime + "  " + rcvNtpTime.toDateString());
		TimeStamp xmitNtpTime = message.getTransmitTimeStamp();
//		System.out.println(" Transmit Timestamp:\t" + xmitNtpTime + "  " + xmitNtpTime.toDateString());
		TimeStamp destNtpTime = TimeStamp.getNtpTime(destTime);
//		System.out.println(" Destination Timestamp:\t" + destNtpTime + "  " + destNtpTime.toDateString());

		info.computeDetails(); 
		Long offsetValue = info.getOffset();
		Long delayValue = info.getDelay();
		
		String delay = (delayValue == null) ? "N/A" : delayValue.toString();
		String offset = (offsetValue == null) ? "N/A" : offsetValue.toString();
		System.out.println(" Roundtrip delay(ms)=" + delay
				+ ", clock offset(ms)=" + offset);
		
	}
	
	public void timereceive() {
		try {
			this.client = new NTPUDPClient();
			client.open();

			InetAddress hostAddr = InetAddress.getByName(timeServer);
			TimeInfo info = client.getTime(hostAddr);

			info.computeDetails();
			offsetValue = info.getOffset();

			System.out.println("clock_offset(ms) = " + offsetValue);
			System.out.println("get Time from ( " + timeServer + " )\n");// +" : "+time

			client.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * public static long getTime(long ntpTimeValue) { long seconds = ntpTimeValue
	 * >>> 32 & 0xFFFFFFFF; long fraction = ntpTimeValue & 0xFFFFFFFF;
	 * 
	 * fraction = Math.round(1000.0D * fraction / 4294967296.0D);
	 * 
	 * long msb = seconds & 0x80000000; if (msb == 0L) { return 2085978496000L +
	 * seconds * 1000L + fraction; }
	 * 
	 * return -2208988800000L + seconds * 1000L + fraction; }
	 */
	
	public void timeSync() throws SocketException {

		try {

			TimeInfo info = client.getTime(hostAddr);
			// ts.timeSync(TimeReception.getTime(info.getReturnTime()));
			TimeStamp ntpTime = TimeStamp.getNtpTime(info.getReturnTime());
		//	ts.timeSync(ntpTime.getTime());

			info.computeDetails();

			// ts.timeSync(info.getReturnTime());
			System.out.println("clock_offset(ms) = " + info.getOffset());
			System.out.println("get Time from ( " + timeServer + " )\n");// +" : "+time


			Thread.sleep(10000);
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}

	}

	public void doSync(){

		try {
		client = new NTPUDPClient();
		client.setDefaultTimeout(10000);
		
		client.open();

		InetAddress hostAddr = InetAddress.getByName(timeServer);

		TimeInfo info = client.getTime(hostAddr);
		
		this.processResponse(info);
		this.processResponse2(info);
		client.close();
		Thread.sleep(10000);
		}catch(Exception e) {
			System.out.println("[NTP]time out");
		}
	}

	public void run() {
		// int count = 0;
		while (true) {

			try {
				this.doSync();
				Thread.sleep(5000);
				// count++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("[NTP]time out in run()");
			}

		}
	}
}

/*
 * public class TimeReception extends Thread implements TimeServer{
 * 
 * public String timeServer=null; public boolean shouldRun; public NTPUDPClient
 * client; public TimeSyncronization ts; public int term;
 * 
 * public TimeReception() { // TODO Auto-generated constructor stub
 * this.shouldRun = true; this.client = new NTPUDPClient(); this.ts = new
 * TimeSyncronization(); this.term = 600000; }
 * 
 * public void setTimeServer(){ Random rand = new Random();
 * 
 * switch(rand.nextInt(5)+1){ case 1: this.timeServer = server1; break; case 2:
 * this.timeServer = server2; break; case 3: this.timeServer = server3; break;
 * case 4: this.timeServer = server4; break; case 5: this.timeServer = server5;
 * break; } }
 * 
 * @Override public void run() { // TODO Auto-generated method stub
 * while(shouldRun){ try { setTimeServer(); client.open();
 * 
 * InetAddress hostAddr = InetAddress.getByName(timeServer); TimeInfo info =
 * client.getTime(hostAddr); info.computeDetails(); offsetValue =
 * info.getOffset();
 * 
 * ts.timeSync((System.currentTimeMillis() + offsetValue));
 * System.out.println("clock_offset(ms) = " + offsetValue);
 * System.out.println("get Time from ( " + timeServer+" )");
 * 
 * Thread.sleep(term); //client.close(); } catch (Exception e) { // TODO
 * Auto-generated catch block e.printStackTrace(); } } } }
 */