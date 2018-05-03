import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class test {

	public static void main(String[] args) throws NoSuchAlgorithmException, SocketException {
		// TODO Auto-generated method stub	
		myIP ip = new myIP("wlan6");
		System.out.println(ip.getMyIP());
        }


}
