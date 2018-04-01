import java.io.*;
import java.net.*;
import java.util.*;

public class myIP {

	public String my_ip;
	
	public myIP(String str) {
		setMyIP(str);
	}
	
    public void setMyIP(String str) {
			try {
				Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
				int i = 100000;
		        String addr = null;
		        
				for (NetworkInterface netint : Collections.list(nets)){
		        
		        if(netint.getName().equals(str)) {

		    		Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
		        
		    		for (InetAddress inetAddress : Collections.list(inetAddresses)) {
		    			if(i > inetAddress.getHostAddress().length()) {
		    				i = inetAddress.getHostAddress().length();
		    				addr = inetAddress.getHostAddress();
		    			}
		    		}
		    	}
		        }
				this.my_ip = addr;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    public String getMyIP() {
    	return my_ip;
    }

    static String displayInterfaceInformation(NetworkInterface netint) throws SocketException {
    	int i = 100000;
        String addr = null;
        
    	if(netint.getName().equals("wlan0")) {

    		Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        
    		for (InetAddress inetAddress : Collections.list(inetAddresses)) {
    			if(i > inetAddress.getHostAddress().length()) {
    				i = inetAddress.getHostAddress().length();
    				addr = inetAddress.getHostAddress();
    			}
    		}
    	}
    	return addr;
     }
}  