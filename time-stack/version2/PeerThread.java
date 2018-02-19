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
    
    /**     * Constructor sets socket 
     *  
     * @param socket Socket with peer 
     */ 
    public PeerThread(Socket socket,Blockchain bc) 
    { 
        this.socket = socket; 
        this.bc = bc;

    } 
 
    /**     * As the name might suggest, each PeerThread runs on its own thread. Additionally, each child network IO thread 
     * runs on its own thread.  
     */ 
    public void run() 
    { 
    	System.out.println("Got connection from " + socket.getInetAddress() + " : "+socket.getPort()); 
        
        inputThread = new InputThread(socket,bc); 
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