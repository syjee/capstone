package client_test;

import java.net.Socket;


public class PeerThread extends Thread{
	private Socket socket;
	public InputThread inputThread;
	
	public PeerThread(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.inputThread = new InputThread(socket);
	}

	public void run(){
		try {
			
			System.out.println("Got connection from " + socket.getInetAddress() + ".");
			inputThread.start();
			inputThread.join();
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("PeerThread running error...");
		}
	}

}
