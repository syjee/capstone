package test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.net.ntp.*;


public class ntp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		NTPUDPClient client = new NTPUDPClient();
		client.open();
		InetAddress hostAddr = InetAddress.getByName("time.bora.net");
		TimeInfo info = client.getTime(hostAddr);
		 	 
//		Long newTimeValue = offsetValue + timeValue;

		
		
		
		/*		
		 * 
		String newTime = (newTimeValue == null) ? "N/A" : newTimeValue.toString();
		String date = (dateValue == null) ? "N/A" :dateValue.toString();
*/	 
		
		
			 
		info.computeDetails(); // compute offset/delay if not already done
		 
		Long offsetValue = info.getOffset();
		Long delayValue = info.getDelay();
		 
		Long return_timeValue = info.getReturnTime();
		InetAddress addressValue = info.getAddress();
		 

		 
		String return_time = (return_timeValue == null) ? "N/A" : return_timeValue.toString();
		String address = (addressValue == null) ? "N/A" : addressValue.toString();
		String delay = (delayValue == null) ? "N/A" : delayValue.toString();
		String offset = (offsetValue == null) ? "N/A" : offsetValue.toString();
		
	
		 /*
		 System.out.println(" Roundtrip delay(ms)=" + delay
		                + ", clock offset(ms)=" + offset + ", InetAddress=" + address+ ", time=" + time
		                + ", return_time=" + return_time + ", Date=" + date); // offset in ms
		                
		 */
		 
		System.out.println("\nRoundtrip delay(ms)= " + delay
	                + "\nclock offset(ms)= " + offset  // offset in ms
	                + "\nInetaddress= " + address
	                + "\nreturn_time(ms)= " + return_time); 
		 
		client.close();
		 
	}

}
