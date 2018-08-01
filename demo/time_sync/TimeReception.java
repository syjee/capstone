import java.net.InetAddress;
import java.text.NumberFormat;
import java.util.*;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpUtils;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;

public class TimeReception implements TimeServer {

	public String timeServer = server4;
	public NTPUDPClient client;
	public TimeSyncronization ts;
	public Long offsetValue;
	public long msNewTime;
	public TimeInfo info;
	public int timer;
	public DB DBHelper = new DB();

	public TimeReception() {
		// TODO Auto-generated constructor stub
		this.client = new NTPUDPClient();
		this.ts = new TimeSyncronization();
		this.timer = 0;
		this.DBHelper.setDB();
	}

	public void initTimeServer() {
		// Random rand = new Random();

		/*
		 * Long time; String tempServer = "";
		 * 
		 * 
		 * this.timeServer = server1; this.timereceive(); tempServer =
		 * timeServer;
		 * 
		 * time = offsetValue;
		 * 
		 * this.timeServer = server2; this.timereceive(); if(Math.abs(time) >
		 * Math.abs(offsetValue)) { time = offsetValue; tempServer = timeServer;
		 * }
		 * 
		 * this.timeServer = server3; this.timereceive(); if(Math.abs(time) >
		 * Math.abs(offsetValue)) { time = offsetValue; tempServer = timeServer;
		 * }
		 * 
		 * this.timeServer = server4; this.timereceive(); if(Math.abs(time) >
		 * Math.abs(offsetValue)) { time = offsetValue; tempServer = timeServer;
		 * }
		 * 
		 * this.timeServer = server5; this.timereceive(); if(Math.abs(time) >
		 * Math.abs(offsetValue)) { time = offsetValue; tempServer = timeServer;
		 * }
		 * 
		 * this.timeServer = tempServer;
		 */
		/*
		 * switch(rand.nextInt(5)+1){ case 1: this.timeServer = server1; break;
		 * case 2: this.timeServer = server2; break; case 3: this.timeServer =
		 * server3; break; case 4: this.timeServer = server4; break; case 5:
		 * this.timeServer = server5; break; }
		 */
		// this.timeServer = server1;
		// System.out.println("[TIMESERVER]"+timeServer);
		// System.out.println("[OFFSET]"+offsetValue);
	}

	public void setTimeServer(String server) {
		this.timeServer = server;
		this.DBHelper.addtoFile(DBHelper.getHistoryFile(), server);
	}

	public String getTimeServer() {
		return this.timeServer;
	}

	public void timereceive() {
		try {
			// setTimeServer();
			client.open();

			InetAddress hostAddr = InetAddress.getByName(timeServer);
			this.info = client.getTime(hostAddr);

			processResponse2(info);

			// info.computeDetails();
			// offsetValue = info.getOffset();

			// System.out.println("[TIMESERVER]"+timeServer);
			// System.out.println("[OFFSET]"+offsetValue);

			// ts.timeSync((System.currentTimeMillis() + offsetValue));
			// System.out.println("clock_offset(ms) = " + offsetValue);
			// System.out.println("get Time from ( " +
			// timeServer+" )");//+" : "+time

			client.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	private final NumberFormat numberFormat = new java.text.DecimalFormat(
			"0.00");

	public void processResponse(TimeInfo info) {
		TimeSyncronization ts = new TimeSyncronization();
		NtpV3Packet message = info.getMessage();

		int stratum = message.getStratum();
		String refType;
		if (stratum <= 0)
			refType = "(Unspecified or Unavailable)";
		else if (stratum == 1)
			refType = "(Primary Reference; e.g., GPS)"; // GPS, radio clock,
														// etc.
		else
			refType = "(Secondary Reference; e.g. via NTP or SNTP)";
		System.out.println(" Stratum: " + stratum + " " + refType);
		int version = message.getVersion();
		int li = message.getLeapIndicator();
		System.out.println(" leap=" + li + ", version=" + version
				+ ", precision=" + message.getPrecision());
		System.out.println(" mode: " + message.getModeName() + " ("
				+ message.getMode() + ")");
		int poll = message.getPoll();
		System.out.println(" poll: "
				+ (poll <= 0 ? 1 : (int) Math.pow(2, poll)) + " seconds"
				+ " (2 ** " + poll + ")");
		double disp = message.getRootDispersionInMillisDouble();
		System.out.println(" rootdelay="
				+ numberFormat.format(message.getRootDelayInMillisDouble())
				+ ", rootdispersion(ms): " + numberFormat.format(disp));

		int refId = message.getReferenceId();

		String refAddr = NtpUtils.getHostAddress(refId);
		/*
		 * String refName = null;
		 * 
		 * 
		 * if (refId != 0) { if (refAddr.equals("127.127.1.0")) { refName =
		 * "LOCAL"; } else if (stratum >= 2) { if
		 * (!refAddr.startsWith("127.127")) { try { InetAddress addr =
		 * InetAddress.getByName(refAddr); String name = addr.getHostName(); if
		 * (name != null && !name.equals(refAddr)) refName = name; } catch
		 * (UnknownHostException e) { refName =
		 * NtpUtils.getReferenceClock(message); } } } else if (version >= 3 &&
		 * (stratum == 0 || stratum == 1)) { refName =
		 * NtpUtils.getReferenceClock(message); } } if (refName != null &&
		 * refName.length() > 1) refAddr += " (" + refName + ")";
		 */

		// System.out.println(" Reference Identifier:\t" + refAddr);
		TimeStamp refNtpTime = message.getReferenceTimeStamp();
		// System.out.println(" Reference Timestamp:\t" + refNtpTime + "  " +
		// refNtpTime.toDateString());
		TimeStamp origNtpTime = message.getOriginateTimeStamp();
		// System.out.println(" Originate Timestamp:\t" + origNtpTime + "  " +
		// origNtpTime.toDateString());
		long destTime = info.getReturnTime();
		TimeStamp rcvNtpTime = message.getReceiveTimeStamp();
		// System.out.println(" Receive Timestamp:\t" + rcvNtpTime + "  " +
		// rcvNtpTime.toDateString());
		TimeStamp xmitNtpTime = message.getTransmitTimeStamp();
		// System.out.println(" Transmit Timestamp:\t" + xmitNtpTime + "  " +
		// xmitNtpTime.toDateString());
		TimeStamp destNtpTime = TimeStamp.getNtpTime(destTime);
		// System.out.println(" Destination Timestamp:\t" + destNtpTime + "  " +
		// destNtpTime.toDateString());

		ts.StringTimeSync(rcvNtpTime.toDateString().substring(17));

		/*
		 * cal.setTimeInMillis(xmitNtpTime.getTime()); String date =
		 * form.format(cal.getTime());
		 * 
		 * //ts.StringDateSync(date);
		 * ts.StringTimeSync(date+xmitNtpTime.toDateString().substring(17));
		 * System.out.println(date + xmitNtpTime.toDateString().substring(17));
		 */

		// ts.StringDateSync("\""+rcvNtpTime.toDateString().substring(0,16)+"\"");
		// System.out.println(rcvNtpTime.toDateString().substring(5,16));

		// ts.OneStepSync(String.valueOf(rcvNtpTime.getTime()).substring(0,10));
		// System.out.println(rcvNtpTime.getDate());
		// ts.StringDateSync(rcvNtpTime.toDateString().substring(5,15));
		// ts.timeSync(rcvNtpTime.getTime() + delayValue);
		// System.out.println(rcvNtpTime.toDateString().substring(17, 28));

		/*
		 * System.out.println("org : " + origNtpTime.toDateString());
		 * System.out.println("rcv : " + rcvNtpTime.toDateString());
		 * System.out.println("xmit : " + xmitNtpTime.toDateString());
		 * System.out.println("dest : " + destNtpTime.toDateString());
		 */
		msNewTime = xmitNtpTime.getTime();
		info.computeDetails();
		offsetValue = info.getOffset();
		Long delayValue = info.getDelay();

		String delay = (delayValue == null) ? "N/A" : delayValue.toString();
		String offset = (offsetValue == null) ? "N/A" : offsetValue.toString();
		System.out.println(" Roundtrip delay(ms)=" + delay
				+ ", clock offset(ms)=" + offset + ", timer(s) = " + timer);

		DBHelper.addtoFile(DBHelper.getHistoryFile(), timer + " : " + offset);
		timer += 10;

	}

	public void processResponse2(TimeInfo info) {
		NtpV3Packet message = info.getMessage();
		/*
		 * int stratum = message.getStratum(); String refType; if (stratum <= 0)
		 * refType = "(Unspecified or Unavailable)"; else if (stratum == 1)
		 * refType = "(Primary Reference; e.g., GPS)"; // GPS, radio clock, etc.
		 * else refType = "(Secondary Reference; e.g. via NTP or SNTP)";
		 * System.out.println(" Stratum: " + stratum + " " + refType); int
		 * version = message.getVersion(); int li = message.getLeapIndicator();
		 * System.out.println(" leap=" + li + ", version=" + version +
		 * ", precision=" + message.getPrecision());
		 * System.out.println(" mode: " + message.getModeName() + " (" +
		 * message.getMode() + ")"); int poll = message.getPoll();
		 * System.out.println(" poll: " + (poll <= 0 ? 1 : (int) Math.pow(2,
		 * poll)) + " seconds" + " (2 ** " + poll + ")"); double disp =
		 * message.getRootDispersionInMillisDouble();
		 * System.out.println(" rootdelay=" +
		 * numberFormat.format(message.getRootDelayInMillisDouble()) +
		 * ", rootdispersion(ms): " + numberFormat.format(disp));
		 */
		int refId = message.getReferenceId();

		String refAddr = NtpUtils.getHostAddress(refId);
		/*
		 * String refName = null;
		 * 
		 * 
		 * if (refId != 0) { if (refAddr.equals("127.127.1.0")) { refName =
		 * "LOCAL"; } else if (stratum >= 2) { if
		 * (!refAddr.startsWith("127.127")) { try { InetAddress addr =
		 * InetAddress.getByName(refAddr); String name = addr.getHostName(); if
		 * (name != null && !name.equals(refAddr)) refName = name; } catch
		 * (UnknownHostException e) { refName =
		 * NtpUtils.getReferenceClock(message); } } } else if (version >= 3 &&
		 * (stratum == 0 || stratum == 1)) { refName =
		 * NtpUtils.getReferenceClock(message); } } if (refName != null &&
		 * refName.length() > 1) refAddr += " (" + refName + ")";
		 */

		// System.out.println(" Reference Identifier:\t" + refAddr);
		TimeStamp refNtpTime = message.getReferenceTimeStamp();
		// System.out.println(" Reference Timestamp:\t" + refNtpTime + "  " +
		// refNtpTime.toDateString());
		TimeStamp origNtpTime = message.getOriginateTimeStamp();
		// System.out.println(" Originate Timestamp:\t" + origNtpTime + "  " +
		// origNtpTime.toDateString());
		long destTime = info.getReturnTime();
		TimeStamp rcvNtpTime = message.getReceiveTimeStamp();
		// System.out.println(" Receive Timestamp:\t" + rcvNtpTime + "  " +
		// rcvNtpTime.toDateString());
		TimeStamp xmitNtpTime = message.getTransmitTimeStamp();
		// System.out.println(" Transmit Timestamp:\t" + xmitNtpTime + "  " +
		// xmitNtpTime.toDateString());
		TimeStamp destNtpTime = TimeStamp.getNtpTime(destTime);
		// System.out.println(" Destination Timestamp:\t" + destNtpTime + "  " +
		// destNtpTime.toDateString());
		
		 
		  		  msNewTime = xmitNtpTime.getTime(); info.computeDetails(); 
		  		  offsetValue = info.getOffset(); Long delayValue = info.getDelay();
		  
		  String delay = (delayValue == null) ? "N/A" : delayValue.toString();
		  String offset = (offsetValue == null) ? "N/A" :
		  offsetValue.toString(); System.out.println(" Roundtrip delay(ms)=" +
		  delay + ", clock offset(ms)=" + offset);
		  
		 

	}

	public void timesynchronize() {
		try {

			this.processResponse(this.info);
			/*
			 * String strValueOffset = offsetValue.toString(); long
			 * longValueOffset = Long.parseLong(strValueOffset); msNewTime =
			 * System.currentTimeMillis() + longValueOffset;
			 * ts.timeSync((msNewTime));
			 */

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
}