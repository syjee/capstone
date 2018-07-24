import java.io.IOException;
import java.net.*;
import java.security.PublicKey;
import java.util.ArrayList;

/**
 * * Class handles all networking after a socket is accepted. Delegates work
 * into two separate threads, one for incoming data, and one for outgoing data,
 * so data in one direction doesn't block data in the other.
 */
public class PeerThread extends Thread {
	private Socket socket;
	public InputThread inputThread;
	public OutputThread outputThread;
	public KeyInputThread keyInputThread;
	public KeyOutputThread keyOutputThread;
	public Blockchain bc;
	public DBHelper db;
	public KeyManager km;
	public PeerNetworkManager pm;
	public TimeManagement tm;
	public RPCManager rm;

	/**
	 * * Constructor sets socket
	 * 
	 * @param socket
	 *            Socket with peer
	 */
	public PeerThread(Socket socket, Blockchain bc) {
		this.socket = socket;
		this.bc = bc;
		this.db = new DBHelper();

	}

	public PeerThread(Socket socket, Blockchain bc, KeyManager km) {
		this.socket = socket;
		this.bc = bc;
		this.db = new DBHelper();
		this.km = km;

	}

	public PeerThread(Socket socket, Blockchain bc, KeyManager km,
			PeerNetworkManager pm, TimeManagement tm,RPCManager rm) {
		this.socket = socket;
		this.bc = bc;
		this.db = new DBHelper();
		this.km = km;

		this.pm = pm;
		this.tm = tm;
		this.rm = rm;
	}

	/**
	 * * As the name might suggest, each PeerThread runs on its own thread.
	 * Additionally, each child network IO thread runs on its own thread.
	 */

	public void addPeerToDB() {
		String remoteHost = socket.getInetAddress() + "";
		remoteHost = remoteHost.replace("/", "");
		remoteHost = remoteHost.replace("\\", "");

		if (db.integrityCheck(db.getPeerListFile(), remoteHost)) {
			System.out.println("[Peer]" + remoteHost + " : " + socket.getPort()
					+ " is alreay existed in the File...");
			this.stop();
			// return;
		} else {
			db.addtoFile(db.getPeerListFile(), remoteHost);
			System.out.println("[Peer]Got connection from "
					+ socket.getInetAddress() + " : " + socket.getPort());
		}
	}

	public void deletePeerFromDB() {
		String remoteHost = socket.getInetAddress() + "";
		remoteHost = remoteHost.replace("/", "");
		remoteHost = remoteHost.replace("\\", "");

		db.deleteLine(db.getPeerListFile(), remoteHost);
	}

	public void run() {
		addPeerToDB();

		inputThread = new InputThread(socket, bc, pm, km, tm,rm);
		inputThread.start();

		outputThread = new OutputThread(socket);
		outputThread.start();

		keyInputThread = new KeyInputThread(socket, km);
		keyInputThread.start();

		keyOutputThread = new KeyOutputThread(socket);
		keyOutputThread.start();

		try {
			Thread.sleep(3000);
			send(km.getPublicKey());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch blocking
			// e.printStackTrace();
		}

	}

	/**
	 * * Used to send data to a peer. Passthrough to outputThread.send()
	 * 
	 * @param data
	 *            String of data to send
	 */

	/*
	 * public void send(String data) { if (outputThread == null) {
	 * System.out.println("Couldn't send " + data + " !!!!"); } else {
	 * outputThread.write(data); } }
	 */

	public void send(Block newBlock) {
		if (outputThread == null) {
			System.out.println("Couldn't send Block : "
					+ newBlock.getCurrentHash().substring(0, 20) + "... !!!!");
		} else {
			outputThread.write(newBlock);
		}
	}

	public void send(PublicKey key) {
		if (keyOutputThread == null) {
			System.out.println("[Peer]Couldn't send Key : " + key.toString()
					+ " !!!!");
		} else {
			keyOutputThread.write(key);
		}
	}

	public void unicast(String host, Block newBlock) {
		if (outputThread == null) {
			System.out.println("[Unicast]Couldn't send Block : "
					+ newBlock.getCurrentHash().substring(0, 20) + "... !!!!");
		} else {
			String remoteHost = socket.getInetAddress() + "";
			remoteHost = remoteHost.replace("/", "");
			remoteHost = remoteHost.replace("\\", "");

			if (host.equals(remoteHost))
				outputThread.write(newBlock);
			else
				System.out.println("[Unicast]Couldn't send Block for the host");
		}
	}

}