package client_test;


public class main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			PeerNetwork peer_network = new PeerNetwork();
			peer_network.start();
			
			peer_network.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("An error is detected at main...");
		}
	}

}
