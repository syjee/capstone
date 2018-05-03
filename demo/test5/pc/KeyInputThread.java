import java.io.*; 
import java.net.*;
import java.security.PublicKey;
import java.util.*; 
 
/** * InputThread only reads data from a peer, and never sends data to prevent blocking and waiting, or some terrible constant back-and-forth keepalive. 
 * All data read in is stored in an ArrayList<String>, with each line stored independently. 
 * Data is accessed through a passthrough all the way through PeerNetwork.  
 */ 
public class KeyInputThread extends Thread 
{ 
    private Socket socket; 
    public DBHelper db;
    public KeyManager km;
    
    //Private instead of public so that object can control calls to receivedData. Acts as a buffer... the same data shouldn't be read more than once. 
//    private ArrayList<String> receivedData = new ArrayList<String>(); 
    
    /**     * Constructor to set class socket variable 
     */ 
    public KeyInputThread(Socket socket) 
    { 
        this.socket = socket; 
    } 
    
    public KeyInputThread(Socket socket,KeyManager km) 
    { 
        this.socket = socket; 
        this.km = km;
    } 
    
    /**     * Constantly reads from the input stream of the socket, and saves any received data to the ArrayList<St 
     */ 
    public void run() 
    { 
        try 
        { 
        	InputStream is = socket.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			PublicKey key;
			
			while((key = (PublicKey)ois.readObject())!=null){
				km.addIdentity(socket.getInetAddress().toString(), key);
				//System.out.println(socket.getInetAddress()+" : "+socket.getPort());
            	System.out.println("[Key]Get PublicKey: " + key.toString().substring(0, 20)); 
            //	System.out.println("size: " + bc.getBlockQueue().size());
            	break;
			}
		
			
        } catch (Exception e) 
        { 
  //          System.out.println("[Peer]Peer " + socket.getInetAddress() + " disconnected.");
        } 
    } 
 
    /**     * Doesn't actually 'read data' as that's done asynchronously in the threadded run function. 
     * However, readData is an easy way to think about it--as receivedData acts as a buffer, holding received data until the daemon is ready to handle it. 
     * Generally, the size of receivedData will be small. However, in some instances (like when downloading many blocks), it can grow quickly. 
     *  
     * @return ArrayList<String> Data pulled from receivedData 
     */ 
}