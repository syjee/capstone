import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class RPC extends Thread{

	public ArrayList<RPCThread>RPCThreads;
	public PeerNetworkManager pm;
	public int listenPort = 8016;
	public boolean shouldRun = true;
	
		
	public RPC(PeerNetworkManager pm) {
		// TODO Auto-generated constructor stub
		RPCThreads = new ArrayList<RPCThread>();
		this.pm = pm;
	}
	
	public void run() 
    { 
        try 
        { 
            ServerSocket listenSocket = new ServerSocket(listenPort); 
            while (shouldRun) //Doesn't actually quit right when shouldRun is changed, as while loop is pending. 
            { 
                RPCThreads.add(new RPCThread(listenSocket.accept(),pm)); 
                RPCThreads.get(RPCThreads.size() - 1).start(); 
            } 
            listenSocket.close(); 
        } catch (Exception e) 
        { 
            //e.printStackTrace(); //Most likely tripped by the inability to bind the listenPort.
            System.out.println("RPC ServerSocket accept error...");
        } 
    }
	
	public void requestToPeer(String peer,int port) 
    {            
			try {
				Socket socket = new Socket(peer,port);  
				OutputStream os = socket.getOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(os);
				ObjectOutputStream oos = new ObjectOutputStream(bos);
				
				oos.writeObject(new Transaction());
				oos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    }
}
