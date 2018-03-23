import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class ethIP {
  public static void main(String[] args) throws SocketException {
	  
    Enumeration<NetworkInterface> eni;
    eni = NetworkInterface.getNetworkInterfaces();
    
    for (NetworkInterface ni : Collections.list(eni)) {
    	if(ni.getName().equals("eth0")){
    		List<InterfaceAddress> addr = ni.getInterfaceAddresses();
    		String[] parts = addr.get(1).toString().split("/");
    		System.out.println(parts[1]);
    	}	  
    }
    
  }
}