
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.security.PublicKey;
import java.util.Scanner;

public class BlockNetworkManager extends Thread {

	public static final int DEFAULT_PORT = 8017;

	public DatagramPacket packet;
	public DatagramSocket socket;

	public DBHelper db;
	public Blockchain bc;

	public KeyManager km;
	public TimeManagement tm;
	public boolean shouldRun = true;

	public BlockNetworkManager(Blockchain bc, TimeManagement tm) {
		try {
			socket = new DatagramSocket(DEFAULT_PORT);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.bc = bc;
		db = new DBHelper();
		this.tm = tm;
	}
	
	public BlockNetworkManager() {
		try {
			socket = new DatagramSocket(DEFAULT_PORT);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db = new DBHelper();
	}


	@Override
	public void run() {
		while (shouldRun) {
			byte[] buf;
			ByteArrayInputStream bis;
			ObjectInput in = null;
			Block newBlock;
			try {
				// TODO Auto-generated method stub
//				System.out.println("["+DEFAULT_PORT+"]HELLO");
				buf = new byte[10240];
				packet = new DatagramPacket(buf, buf.length);

				socket.receive(packet);
				packet.setLength(packet.getLength());
				//System.out.println("[UDP]Received from: " + packet.getAddress() + ":" + packet.getPort());
				byte[] buffer = packet.getData();

				bis = new ByteArrayInputStream(buffer);
				in = new ObjectInputStream(bis);
				
				
				newBlock = (Block) in.readObject();
				
	//			System.out.println("[UDP]new Block");
				if(newBlock.getCurrentHash().length() > 20)
					System.out.println("["+DEFAULT_PORT+"]Get Block::" + newBlock.getCurrentHash().substring(0,20)+" ... from "+packet.getAddress().toString());

				else
					System.out.println("["+DEFAULT_PORT+"]Get Block::" + newBlock.getCurrentHash()+" from "+packet.getAddress().toString());


/*				
				while ((newBlock = (Block) in.readObject()) != null) {
					bc.addToBlockQueue(newBlock);

					if(newBlock.getCurrentHash().length() > 20)
						System.out.println("["+DEFAULT_PORT+"]Get Block::" + newBlock.getCurrentHash().substring(0,20)+" ... from "+packet.getAddress().toString());

					else
						System.out.println("["+DEFAULT_PORT+"]Get Block::" + newBlock.getCurrentHash()+" from "+packet.getAddress().toString());

					break;
				}
	*/			
//				newBlock = (Block) in.readObject();
				bc.addToBlockQueue(newBlock);
							
			} catch (Exception e) {
				socket.close();
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				e.printStackTrace();
				System.out.println("["+DEFAULT_PORT+"] disconnected.");
			}
		}
	}

	public synchronized void broadcast(Block newBlock) {
		// pn.broadcast(newBlock);
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput out = new ObjectOutputStream(bos);
			out.writeObject(newBlock);
			byte[] b = bos.toByteArray();

			InetAddress host = InetAddress.getByName("192.168.0.255");
			DatagramSocket bsocket = new DatagramSocket();
			bsocket.setBroadcast(true);

			DatagramPacket packet = new DatagramPacket(b, b.length, host, DEFAULT_PORT);
			bsocket.send(packet);
			
			if(newBlock.getCurrentHash().length()>20)
				System.out.println("[" + DEFAULT_PORT + "]Broadcast Block::" + newBlock.getCurrentHash().substring(0,20)+"...");
			else 
				System.out.println("[" + DEFAULT_PORT + "]Broadcast Block::" + newBlock.getCurrentHash().toString());
			
			bsocket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
