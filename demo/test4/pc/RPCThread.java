import java.net.*;
import java.util.ArrayList; 

/** * Class handles all networking after a socket is accepted. Delegates work into two separate threads, 
 * one for incoming data, and one for outgoing data, so data in one direction doesn't block data in 
 * the other. 
 */ 
public class RPCThread extends Thread 
{ 
    private Socket socket; 
    public RPCInput inputThread; 
    public RPCOutput outputThread;
    public PeerNetworkManager pm;
    public DBHelper db;
    public KeyManager km;
    /**     * Constructor sets socket 
     *  
     * @param socket Socket with peer 
     */ 
    public RPCThread(Socket socket) 
    { 
        this.socket = socket;
        this.db = new DBHelper();
    }
    
    public RPCThread(Socket socket,PeerNetworkManager pm,KeyManager km) 
    { 
        this.socket = socket; 
        this.pm = pm;
        this.db = new DBHelper();
        this.km = km;

    }
    /**     * As the name might suggest, each PeerThread runs on its own thread. Additionally, each child network IO thread 
     * runs on its own thread.  
     */ 
    
    public void addRPCToDB(){
		String remoteHost = socket.getInetAddress() + ""; 
		remoteHost = remoteHost.replace("/", ""); 
		remoteHost = remoteHost.replace("\\", ""); 
    
		if(db.integrityCheck(db.getRPCListFile(), remoteHost)) { 
			System.out.println("[RPC]"+remoteHost + " : "+socket.getPort()+ " is alreay existed in the File...");
			return;
		} 
		else{
			db.addtoFile(db.getRPCListFile(), remoteHost);
			System.out.println("[RPC]Got connection from " + socket.getInetAddress() + " : "+socket.getPort());
		}
    }
    
    public void run() 
    { 
    	addRPCToDB();
    	
        inputThread = new RPCInput(socket,pm,km); 
        inputThread.start(); 
        
        outputThread = new RPCOutput(socket); 
        outputThread.start();   
    } 
 
    /**     * Used to send data to a peer. Passthrough to outputThread.send() 
     *  
     * @param data String of data to send 
     */
    
    /*
    public void send(String data) 
    { 
        if (outputThread == null) 
        { 
            System.out.println("Couldn't send " + data + " !!!!"); 
        } 
        else 
        { 
            outputThread.write(data); 
        } 
    }
    */
    
    public void send(Transaction newTranaction) 
    { 
        if (outputThread == null) 
        { 
            System.out.println("Couldn't send " + newTranaction + " !!!!"); 
        } 
        else 
        { 
            outputThread.write(newTranaction); 
        } 
    }
}