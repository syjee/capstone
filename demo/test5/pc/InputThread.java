import java.io.*;
import java.net.*;
import java.util.*;

/**
 * * InputThread only reads data from a peer, and never sends data to prevent
 * blocking and waiting, or some terrible constant back-and-forth keepalive. All
 * data read in is stored in an ArrayList<String>, with each line stored
 * independently. Data is accessed through a passthrough all the way through
 * PeerNetwork.
 */
public class InputThread extends Thread {
	public static final int DEFAULT_PORT = 8017;
	private Socket socket;
	public Blockchain bc;
	public DBHelper db;
	public DatagramSocket dSocket;
	
	public ByteArrayInputStream bis;
	public ObjectInput in;
	// Private instead of public so that object can control calls to receivedData.
	// Acts as a buffer... the same data shouldn't be read more than once.
	// private ArrayList<String> receivedData = new ArrayList<String>();
	private ArrayList<Block> receivedBlock = new ArrayList<Block>();

	/**
	 * * Constructor to set class socket variable
	 * @throws SocketException 
	 */
	public InputThread(Socket socket, Blockchain bc) throws SocketException {
		this.socket = socket;
		this.bc = bc;
		this.db = new DBHelper();
		
		this.bis = null;
		this.in = null;
		this.dSocket = new DatagramSocket(DEFAULT_PORT);
	}

	/**
	 * * Constantly reads from the input stream of the socket, and saves any
	 * received data to the ArrayList<St
	 */

	public void deletePeerFromDB() {
		String remoteHost = socket.getInetAddress() + "";
		remoteHost = remoteHost.replace("/", "");
		remoteHost = remoteHost.replace("\\", "");

		db.deleteLine(db.getPeerListFile(), remoteHost);
	}

	public void run() {
		
		DatagramPacket packet;
		while(true) {
		try {
			/*
			InputStream is = socket.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			ObjectInputStream ois = new ObjectInputStream(bis);
*/
			
			byte[] buf = new byte[1024];
			packet = new DatagramPacket(buf, buf.length);
			
			dSocket.receive(packet);
			packet.setLength(packet.getLength());
			System.out.println("Received from: " + packet.getAddress() + ":" + packet.getPort());
			byte[] buffer = packet.getData();

			bis = new ByteArrayInputStream(buffer);
			in = new ObjectInputStream(bis);
	
			Block newBlock;

			while ((newBlock = (Block) in.readObject()) != null) {
				bc.addToBlockQueue(newBlock);

				// System.out.println(socket.getInetAddress()+" : "+socket.getPort());
				System.out.println("[Peer]Get Block:" + newBlock.getCurrentHash());
				break;
				// System.out.println("size: " + bc.getBlockQueue().size());
			}

			in.close();
			bis.close();
			
			//			ois.close();

		} catch (Exception e) {
			deletePeerFromDB();
			System.out.println("[Peer]Peer " + socket.getInetAddress() + " disconnected.");
		}
		}
	}

	/**
	 * * Doesn't actually 'read data' as that's done asynchronously in the threadded
	 * run function. However, readData is an easy way to think about it--as
	 * receivedData acts as a buffer, holding received data until the daemon is
	 * ready to handle it. Generally, the size of receivedData will be small.
	 * However, in some instances (like when downloading many blocks), it can grow
	 * quickly.
	 * 
	 * @return ArrayList<String> Data pulled from receivedData
	 */
	@SuppressWarnings("unused")
	public ArrayList<Block> readData() {
		System.out.println("readData() called!");
		System.out.println("We have " + receivedBlock.size() + " pieces!");
		// Don't want to mess with the ArrayList while run() is modifying it.
		ArrayList<Block> inputBuffer = new ArrayList<Block>(receivedBlock);
		if (inputBuffer == null) {
			inputBuffer = new ArrayList<Block>();
		}
		receivedBlock = new ArrayList<Block>(); // Resets 'buffer'
		return inputBuffer;
	}
}