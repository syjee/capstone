import java.net.*;
import java.util.ArrayList; 

/** * Class handles all networking after a socket is accepted. Delegates work into two separate threads, 
 * one for incoming data, and one for outgoing data, so data in one direction doesn't block data in 
 * the other. 
 */ 
public class PeerThread extends Thread 
{ 
    private Socket socket; 
    public InputThread inputThread; 
    public OutputThread outputThread; 
    public Blockchain bc;
    public DBHelper db;

    
    /**     * Constructor sets socket 
     *  
     * @param socket Socket with peer 
     */ 
    public PeerThread(Socket socket,Blockchain bc) 
    { 
        this.socket = socket; 
        this.bc = bc;
        this.db = new DBHelper();

    } 

    public PeerThread(Socket socket,Blockchain bc,DBHelper db) 
    { 
        this.socket = socket; 
        this.bc = bc;
        this.db = db;

        
        
    } 
 
    /**     * As the name might suggest, each PeerThread runs on its own thread. Additionally, each child network IO thread 
     * runs on its own thread.  
     */ 
    
    public void addPeerToDB(){
    		String remoteHost = socket.getInetAddress() + ""; 
    		remoteHost = remoteHost.replace("/", ""); 
    		remoteHost = remoteHost.replace("\\", ""); 

    		if(db.integrityCheck(db.getPeerListFile(), remoteHost)) { 
    			System.out.println("[Peer]"+remoteHost + " : "+socket.getPort()+ " is alreay existed in the File...");
    			return;
    		} 
    		else{
    			db.addtoFile(db.getPeerListFile(), remoteHost);
    			System.out.println("[Peer]Got connection from " + socket.getInetAddress() + " : "+socket.getPort());
    		}

    }
    
    public void run() 
    {   
    	addPeerToDB();
    	
   		inputThread = new InputThread(socket,bc,db); 
   		inputThread.start(); 
        
   		outputThread = new OutputThread(socket); 
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
    
    public void send(Block newBlock) 
    { 
        if (outputThread == null) 
        { 
            System.out.println("Couldn't send " + newBlock + " !!!!"); 
        } 
        else 
        { 
            outputThread.write(newBlock); 
        } 
    } 
}