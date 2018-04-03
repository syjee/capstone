import java.io.ByteArrayOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HostnameVerifier;

public class Broadcast {

	public static final int DEFAULT_PORT = 1234;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Transaction t = new Transaction();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;

		out = new ObjectOutputStream(bos);
		out.writeObject(t);
		byte[] b = bos.toByteArray();

		myIP myip = new myIP("wlan0");
		String ip = myip.getMyIP();
		System.out.println(ip);
		InetAddress host = InetAddress.getByName("192.168.0.255");
		DatagramSocket socket = new DatagramSocket();
		socket.setBroadcast(true);

		DatagramPacket packet = new DatagramPacket(b, b.length, host, DEFAULT_PORT);
		System.out.println("object lenght : " + b.length);
		System.out.println("send packet : " + t.getTransaction());
		socket.send(packet);

		socket.close();

	}

}
