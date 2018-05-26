import java.io.IOException;
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
	    
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		myIP myip = new myIP("wlan0");
		System.out.println(myip.getMyIP());
		
		try
        {
            socket = new DatagramSocket(DEFAULT_PORT);
        }
        catch( Exception ex )
        {
            System.out.println("Problem creating socket on port: " + DEFAULT_PORT );
        }

		byte[] buf = new byte[1024];
        packet = new DatagramPacket (buf,buf.length);
        
        while (true)
        {
            try
            {
                socket.receive (packet);
                packet.setLength(packet.getLength());
                System.out.println("Received from: " + packet.getAddress () + ":" +
                                   packet.getPort ());
                byte[] buffer = packet.getData();
                String msg = new String(buffer,StandardCharsets.UTF_8);
                System.out.println("msg length : "+msg.length()+", byte length : "+packet.getData().length);	
                System.out.println("packet length : "+ packet.getLength() + "receive packet : " + msg);
                /*
                byte[] outBuffer = new java.util.Date ().toString ().getBytes ();
                packet.setData (outBuffer);
                packet.setLength (outBuffer.length);
                socket.send (packet);
                */
            }
            catch (IOException ie)
            {
                ie.printStackTrace();
            }
	}
	}
}
