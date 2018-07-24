import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;


public class test implements TimeServer{

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		NTPUDPClient client = new NTPUDPClient();
		Long offsetValue;
		String timeServer = server3;
		client.open();

		InetAddress hostAddr = InetAddress.getByName(timeServer);
		TimeInfo info = client.getTime(hostAddr);
		info.computeDetails();
		offsetValue = info.getOffset();
		
		System.out.println("[TIMESERVER]"+timeServer);
		System.out.println("[OFFSET]"+offsetValue);
		
	}

}
