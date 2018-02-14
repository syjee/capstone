import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		PeerNetwork peerNetwork = new PeerNetwork();
		peerNetwork.start(); 
		
		peerNetwork.connectToPeer("192.168.0.3", 8015);
		
		Thread.sleep(1000);
		
		Block b = new Block();
		b.setTransaction("Welcome to PeerNetwork");
		
		peerNetwork.broadcast(b);
		
	}

}
