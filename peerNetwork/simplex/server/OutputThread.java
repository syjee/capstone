package server_test;

import java.net.Socket;

import java.io.*;


public class OutputThread extends Thread{

	private Socket socket;
	public OutputStream output_data;

	public OutputThread(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		try {
			output_data = socket.getOutputStream();
			write();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("OutputThread running error...");
		}
		
	}
	
	public void write(){
		try {
			
			String sendDataString = "One!!";
			output_data.write(sendDataString.getBytes());
			sendDataString = "Two!!";
			output_data.write(sendDataString.getBytes());
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("OutputThread write() error...");
		}
	}
	
}

