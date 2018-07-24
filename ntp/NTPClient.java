import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpUtils;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;
public final class NTPClient {
	
	private static final NumberFormat numberFormat = new java.text.DecimalFormat("0.00");
	
	public static void processResponse(TimeInfo info) {
		TimeSyncronization ts = new TimeSyncronization();
		NtpV3Packet message = info.getMessage();
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
		int refId = message.getReferenceId();
		String refAddr = NtpUtils.getHostAddress(refId);
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
		System.out.println(" Reference Identifier:\t" + refAddr);
		TimeStamp refNtpTime = message.getReferenceTimeStamp();
		System.out.println(" Reference Timestamp:\t" + refNtpTime + "  " + refNtpTime.toDateString());
		TimeStamp origNtpTime = message.getOriginateTimeStamp();
		System.out.println(" Originate Timestamp:\t" + origNtpTime + "  " + origNtpTime.toDateString());
		long destTime = info.getReturnTime();
		TimeStamp rcvNtpTime = message.getReceiveTimeStamp();
		System.out.println(" Receive Timestamp:\t" + rcvNtpTime + "  " + rcvNtpTime.toDateString());
		TimeStamp xmitNtpTime = message.getTransmitTimeStamp();
		System.out.println(" Transmit Timestamp:\t" + xmitNtpTime + "  " + xmitNtpTime.toDateString());
		TimeStamp destNtpTime = TimeStamp.getNtpTime(destTime);
		
		info.computeDetails(); 
		Long offsetValue = info.getOffset();
		Long delayValue = info.getDelay();
		
		ts.timeSync(destNtpTime.getTime()+info.getDelay());
		System.out.println(" Destination Timestamp:\t" + destNtpTime + "  " + destNtpTime.toDateString());
		
		
		String delay = (delayValue == null) ? "N/A" : delayValue.toString();
		String offset = (offsetValue == null) ? "N/A" : offsetValue.toString();
		System.out.println(" Roundtrip delay(ms)=" + delay
				+ ", clock offset(ms)=" + offset);
	}
	public static final void main(String[] args)
	{
		NTPUDPClient client = new NTPUDPClient();
		args = new String[]{"south-america.pool.ntp.org"};
		client.setDefaultTimeout(10000);
		try {
			client.open();
			for (int i = 0; i < args.length; i++)
			{
				System.out.println();
				try {
					InetAddress hostAddr = InetAddress.getByName(args[i]);
					System.out.println("> " + hostAddr.getHostName() + "/" + hostAddr.getHostAddress());
					TimeInfo info = client.getTime(hostAddr);
					processResponse(info);
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		client.close();
	}
}