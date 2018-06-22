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
	private Socket socket;
	public Blockchain bc;
	public DBHelper db;
	public PeerNetworkManager pm;
	public KeyManager km;
	public TimeManagement tm;
	public RPCManager rm;
	// Private instead of public so that object can control calls to
	// receivedData. Acts as a buffer... the same data shouldn't be read more
	// than once.
	// private ArrayList<String> receivedData = new ArrayList<String>();
	private ArrayList<Block> receivedBlock = new ArrayList<Block>();

	public InputThread(Socket socket, Blockchain bc) {
		this.socket = socket;
		this.bc = bc;
		this.db = new DBHelper();
	}

	public InputThread(Socket socket, Blockchain bc, PeerNetworkManager pm,
			KeyManager km, TimeManagement tm,RPCManager rm) {
		this.socket = socket;
		this.bc = bc;
		this.db = new DBHelper();
		this.pm = pm;
		this.km = km;

		this.tm = tm;
		this.rm = rm;
	}

	public void deletePeerFromDB() {
		String remoteHost = socket.getInetAddress() + "";
		remoteHost = remoteHost.replace("/", "");
		remoteHost = remoteHost.replace("\\", "");

		db.deleteLine(db.getPeerListFile(), remoteHost);
	}

	public void run() {
		while (true) {
			InputStream is = null;
			BufferedInputStream bis = null;
			ObjectInputStream ois = null;
			try {
				Thread.sleep(5000);
				is = socket.getInputStream();
				bis = new BufferedInputStream(is);
				ois = new ObjectInputStream(bis);

				Block newBlock;

				System.out.println("[InputThread]wait for newBlock...");

				while ((newBlock = (Block) ois.readObject()) != null) {
					// bc.addToBlockQueue(newBlock);

					// System.out.println(socket.getInetAddress()+" : "+socket.getPort());

					if (newBlock.getTransaction().getTransaction().length() > 20) {
						System.out.println("[Peer]Get Transaction in Block:"
								+ newBlock.getTransaction().getTransaction()
										.substring(0, 20) + " from "
								+ socket.getInetAddress() + " : "
								+ socket.getPort());

					} else {
						System.out.println("[Peer]Get Transaction in Block:"
								+ newBlock.getTransaction().getTransaction()
								+ " from " + socket.getInetAddress() + " : "
								+ socket.getPort());
					}

					tm.addTransaction(newBlock.getTransaction(), socket
							.getInetAddress().toString());

					rm.request(3,newBlock);
					
					// Thread.sleep(10000);
					// tm.mTime.get(tm.mTime.size()-1).stopWatch.pause();

					// System.out.println("size: " + bc.getBlockQueue().size());
					// break;
				}

			} catch (Exception e) {

				deletePeerFromDB();
				System.out.println("[Peer]Peer " + socket.getInetAddress()
						+ " disconnected.");
				break;
			}
		}
	}

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
/*
 * 
 * public class InputThread extends Thread { public static final int
 * DEFAULT_PORT = 8017; private Socket socket; public Blockchain bc; public
 * DBHelper db; public DatagramSocket dSocket;
 * 
 * public ByteArrayInputStream bis; public ObjectInput in; // Private instead of
 * public so that object can control calls to receivedData. // Acts as a
 * buffer... the same data shouldn't be read more than once. // private
 * ArrayList<String> receivedData = new ArrayList<String>(); private
 * ArrayList<Block> receivedBlock = new ArrayList<Block>();
 * 
 * 
 * public InputThread(Socket socket, Blockchain bc) throws SocketException {
 * this.socket = socket; this.bc = bc; this.db = new DBHelper();
 * 
 * this.bis = null; this.in = null; this.dSocket = new
 * DatagramSocket(DEFAULT_PORT); }
 * 
 * 
 * 
 * public void deletePeerFromDB() { String remoteHost = socket.getInetAddress()
 * + ""; remoteHost = remoteHost.replace("/", ""); remoteHost =
 * remoteHost.replace("\\", "");
 * 
 * db.deleteLine(db.getPeerListFile(), remoteHost); }
 * 
 * public void run() {
 * 
 * DatagramPacket packet; while(true){ try {
 * 
 * InputStream is = socket.getInputStream(); BufferedInputStream bis = new
 * BufferedInputStream(is); ObjectInputStream ois = new ObjectInputStream(bis);
 * 
 * 
 * byte[] buf = new byte[1024]; packet = new DatagramPacket(buf, buf.length);
 * 
 * dSocket.receive(packet); packet.setLength(packet.getLength());
 * System.out.println("Received from: " + packet.getAddress() + ":" +
 * packet.getPort()); byte[] buffer = packet.getData();
 * 
 * bis = new ByteArrayInputStream(buffer); in = new ObjectInputStream(bis);
 * 
 * Block newBlock = (Block) in.readObject();
 * if(newBlock.getCurrentHash().length() > 20)
 * System.out.println("["+DEFAULT_PORT+"]Get Block::" +
 * newBlock.getCurrentHash()
 * .substring(0,20)+" ... from "+packet.getAddress().toString());
 * 
 * else System.out.println("["+DEFAULT_PORT+"]Get Block::" +
 * newBlock.getCurrentHash()+" from "+packet.getAddress().toString());
 * 
 * 
 * while ((newBlock = (Block) in.readObject()) != null) {
 * bc.addToBlockQueue(newBlock);
 * 
 * // System.out.println(socket.getInetAddress()+" : "+socket.getPort());
 * System.out.println("[Peer]Get Block:" + newBlock.getCurrentHash()); //
 * System.out.println("size: " + bc.getBlockQueue().size()); break; }
 * 
 * 
 * in.close(); bis.close(); // ois.close();
 * 
 * } catch (Exception e) { deletePeerFromDB(); System.out.println("[Peer]Peer "
 * + socket.getInetAddress() + " disconnected."); break; } } }
 * 
 * @SuppressWarnings("unused") public ArrayList<Block> readData() {
 * System.out.println("readData() called!"); System.out.println("We have " +
 * receivedBlock.size() + " pieces!"); // Don't want to mess with the ArrayList
 * while run() is modifying it. ArrayList<Block> inputBuffer = new
 * ArrayList<Block>(receivedBlock); if (inputBuffer == null) { inputBuffer = new
 * ArrayList<Block>(); } receivedBlock = new ArrayList<Block>(); // Resets
 * 'buffer' return inputBuffer; } }
 */