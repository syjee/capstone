package server_test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;


public class PeerNetwork extends Thread{
	
	public int listenPort;
	public boolean shouldRun = true;
	public ArrayList<PeerThread> peerThreads;
	
	public PeerNetwork(){
		this.listenPort = 8015;
		this.peerThreads = new ArrayList<PeerThread>();
	}
	
	public void run(){
		try {
			ServerSocket listenSocket = new ServerSocket(listenPort);
			while(shouldRun){
				peerThreads.add(new PeerThread(listenSocket.accept()));
				peerThreads.get(peerThreads.size()-1).start();
				
				peerThreads.get(peerThreads.size()-1).join();
				this.shouldRun = false;
			}
			listenSocket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("PeerNetwork running error...");
		}
		
	}

}
