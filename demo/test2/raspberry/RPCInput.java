import java.io.*; 
import java.net.*; 
import java.util.*; 
 
/** * InputThread only reads data from a peer, and never sends data to prevent blocking and waiting, or some terrible constant back-and-forth keepalive. 
 * All data read in is stored in an ArrayList<String>, with each line stored independently. 
 * Data is accessed through a passthrough all the way through PeerNetwork.  
 */ 
public class RPCInput extends Thread 
{ 
    private Socket socket; 
    public BlockGenerator bg;
    public PeerNetworkManager pm;
    
    //Private instead of public so that object can control calls to receivedData. Acts as a buffer... the same data shouldn't be read more than once. 
//    private ArrayList<String> receivedData = new ArrayList<String>(); 
    private ArrayList<Transaction> receivedTransaction = new ArrayList<Transaction>();
    
    /**     * Constructor to set class socket variable 
     */ 
    public RPCInput(Socket socket) 
    { 
        this.socket = socket; 
    }
    
    public RPCInput(Socket socket,PeerNetworkManager pm) 
    { 
        this.socket = socket; 
        this.pm = pm;
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
			
			Transaction newTransaction;
			
			while((newTransaction = (Transaction)ois.readObject())!=null){
				System.out.println(socket.getInetAddress()+" : "+socket.getPort());
            	System.out.println("RUN(): " + newTransaction.getTransaction());
            	bg = new BlockGenerator(newTransaction, pm);
            	bg.start();
			}
			ois.close();
			
        } catch (Exception e) 
        { 
            System.out.println("Peer " + socket.getInetAddress() + " disconnected."); 
        } 
    } 
 
    /**     * Doesn't actually 'read data' as that's done asynchronously in the threadded run function. 
     * However, readData is an easy way to think about it--as receivedData acts as a buffer, holding received data until the daemon is ready to handle it. 
     * Generally, the size of receivedData will be small. However, in some instances (like when downloading many blocks), it can grow quickly. 
     *  
     * @return ArrayList<String> Data pulled from receivedData 
     */ 
    @SuppressWarnings("unused") 
 public ArrayList<Transaction> readData() 
    { 
        System.out.println("readData() called!"); 
        System.out.println("We have " + receivedTransaction.size() + " pieces!"); 
        //Don't want to mess with the ArrayList while run() is modifying it. 
        ArrayList<Transaction> inputBuffer = new ArrayList<Transaction>(receivedTransaction); 
        if (inputBuffer == null) 
        { 
            inputBuffer = new ArrayList<Transaction>(); 
        } 
        receivedTransaction = new ArrayList<Transaction>(); //Resets 'buffer' 
        return inputBuffer; 
    } 
}
