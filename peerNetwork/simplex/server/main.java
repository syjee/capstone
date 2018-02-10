package server_test;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			PeerNetwork peer_network = new PeerNetwork();
			peer_network.start();
			
			peer_network.join();
			System.out.println("PeerNetwork is ended properly...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("An error is detected at main...");
		}
		
		

	}

}
