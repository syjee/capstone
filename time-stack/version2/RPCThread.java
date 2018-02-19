import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import org.apache.commons.net.nntp.NewsgroupInfo;

public class RPCThread extends Thread{

	public Socket socket;
	public Transaction request;
	public PeerNetworkManager pm;
	public BlockGenerator bg;
	
	
	public RPCThread(Socket socket, PeerNetworkManager pm) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.pm = pm;
	}
	
	public void setRequest(Transaction trans) {
		this.request = trans;
	}
	
	public void setBlockGenerator() {
		bg = new BlockGenerator(request,pm); 
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try 
        { 
        	InputStream is = socket.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			Transaction data;
			
			while((data = (Transaction)ois.readObject())!=null){
            	System.out.println("Get Request from "+socket.getInetAddress()+" : "+socket.getPort()); 
            	setRequest(data);
            	setBlockGenerator();
            	bg.start();
			}
			ois.close();
			
        } catch (Exception e) 
        { 
            System.out.println("Peer " + socket.getInetAddress() + " disconnected."); 
        } 
	}
	
}
