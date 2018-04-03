import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class test {

	/**
	 * @param args
	 * @throws InterruptedException
	 */

	public static final int DEFAULT_PORT = 1234;
	private static DatagramSocket socket;
	private static DatagramPacket packet;

	public static void main(String[] args) throws InterruptedException,
			ClassNotFoundException {
		// TODO Auto-generated method stub

		myIP myip = new myIP("wlan0");
		System.out.println(myip.getMyIP());

		try {
			socket = new DatagramSocket(DEFAULT_PORT);
		} catch (Exception ex) {
			System.out.println("Problem creating socket on port: "
					+ DEFAULT_PORT);
		}

		byte[] buf = new byte[1024];
		packet = new DatagramPacket(buf, buf.length);

		while (true) {
			try {
				socket.receive(packet);
				packet.setLength(packet.getLength());
				System.out.println("Received from: " + packet.getAddress()
						+ ":" + packet.getPort());
				byte[] buffer = packet.getData();

				ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
				ObjectInput in = null;

				in = new ObjectInputStream(bis);
				Transaction t = (Transaction) in.readObject();

				System.out.println("packet length : " + packet.getLength());
				System.out.println("receive packet : " + t.getTransaction());

			} catch (IOException ie) {
				ie.printStackTrace();
				socket.close();
			}
		}
		
	}
}
