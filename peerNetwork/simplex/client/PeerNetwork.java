package client_test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class PeerNetwork extends Thread{
	
	public int listenPort;
	public boolean shouldRun = true;
	public ArrayList<PeerThread> peerThreads;
	
	public PeerNetwork(){
		this.listenPort = 8015;
		this.peerThreads = new ArrayList<PeerThread>();
	}
	
	/*
	public void run(){
		try {
			ServerSocket listenSocket = new ServerSocket(listenPort);
			while(shouldRun){
				peerThreads.add(new PeerThread(listenSocket.accept()));
				peerThreads.get(peerThreads.size()-1).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("PeerNetwork running error...");
		}
		
	}
	*/
	public void run(){
		Socket clientSocket;
		try {
			clientSocket = new Socket("192.168.0.11",8015);
			while(shouldRun){
				peerThreads.add(new PeerThread(clientSocket));
				peerThreads.get(peerThreads.size()-1).start();
				
				peerThreads.get(peerThreads.size()-1).join();
				this.shouldRun = false;
			}
			
			clientSocket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("PeerNetwork running error...");
		}
		
	}

}
