package server_test;

import java.io.IOException;
import java.net.Socket;


public class PeerThread extends Thread{
	private Socket socket;
	public OutputThread outputThread; 
	
	public PeerThread(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		outputThread = new OutputThread(socket);
	}

	public void run(){
		try {
			System.out.println("Got connection from " + socket.getInetAddress() + ".");
			
			outputThread.start();
			outputThread.join();
			/*
			OutputStream output_data = socket.getOutputStream();
			
			String sendDataString = "wellcome to peerNetwork!!";
			output_data.write(sendDataString.getBytes());
			*/
			socket.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("PeerThread running error...");
		}
	}

}
