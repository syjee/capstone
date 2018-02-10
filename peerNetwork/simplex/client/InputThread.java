package client_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class InputThread extends Thread{
	private Socket socket;
	public BufferedReader in;
	
	public InputThread(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("InputThread running error...");
		}
		
	}
	
	public void read(){
		String input; 
		
		try {
			while((input = in.readLine())!= null){
				System.out.println(input);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("InputThread reading error...");
		}
		
	}

}
