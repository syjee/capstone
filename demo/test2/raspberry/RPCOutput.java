import java.io.*; 
import java.net.*; 
import java.util.*; 
 
/** * OutputThread writes data to a peer, and never reads in any data in order to prevent blocking and waiting, or some terrible constant back-and-forth keepalive. 
 * Data written isn't saved anywhere. This behavior doesn't need to be run in a thread, but would block activity on the main thread if write calls were direct. 
 * Instead, any calls to OutputThread's external methods are extremely lightweight (putting a String in a buffer) rather than waiting on network IO to execute.  
 */ 
public class RPCOutput extends Thread 
{ 
    private Socket socket; 
 
    //Private to mirror InputThread's structure. For OOP model, it makes more sense for a method to simulate 'writing' data (even though it is delayed until the thread writes the data).  
 
//    private ArrayList<String> outputBuffer;
    private ArrayList<Transaction> outputBuffer;
    private boolean shouldContinue = true; 
 
    /**     * Constructor to set class socket variable 
     */ 
    public RPCOutput(Socket socket) 
    { 
        this.socket = socket; 
    } 
 
    /**     * Constantly checks outputBuffer for contents, and writes any contents in outputBuffer. 
     */ 
    public void run() 
    { 
        try 
        { 
            outputBuffer = new ArrayList<Transaction>(); 
            
            OutputStream os = socket.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			
//            PrintWriter out = new PrintWriter(socket.getOutputStream(),true); 
            while (shouldContinue) 
            { 
          
                if (outputBuffer.size() > 0) 
                { 
                    if (outputBuffer.get(0) != null) 
                    { 
                        for (int i = 0; i < outputBuffer.size(); i++) 
                        { 
                        	System.out.println("Sending " + outputBuffer.get(i).getTransaction() + " to " + socket.getInetAddress()); 
                            
                            oos.writeObject(outputBuffer.get(i));
//                            oos.writeBoolean(true);
                            oos.flush();
//                            oos.writeBoolean(true);
//                            out.println(outputBuffer.get(i)); 
                        } 
                        outputBuffer = new ArrayList<Transaction>(); 
                        outputBuffer.add(null); 
                    } 
                } 
                Thread.sleep(100); 
            } 
            oos.close();
            System.out.println("WHY AM I HERE"); 
        } catch (Exception e) 
        { 
            e.printStackTrace(); 
        } 
    } 
 
    /**     * Technically not writing to the network socket, but instead putting the passed-in data in a buffer to be written to the socket as soon as possible. 
     *  
     * @param data Data to write 
     */ 
    public void write(Transaction data) 
    { 
        if (data.getTransaction().length() > 20) 
        { 
            System.out.println("PUTTING INTO WRITE BUFFER: " + data.getTransaction().substring(0, 20) + "..."); 
        } 
        else 
        { 
            System.out.println("PUTTING INTO WRITE BUFFER: " + data.getTransaction()); 
        } 
        File f = new File("writebuffer"); 
        try 
        { 
            PrintWriter out = new PrintWriter(f); 
            out.println("SENDING: " + data.getTransaction()); 
            out.close(); 
        } catch (Exception e) 
        { 
        } 
        if (outputBuffer.size() > 0) 
        { 
            if (outputBuffer.get(0) == null) 
            { 
                outputBuffer.remove(0); 
            }  
        } 
        outputBuffer.add(data); 
    } 
 
    /**     * Stops thread during the next write cycle. I couldn't call it stop() like I wanted to, cause you can't overwrite that method of Thread. :'( 
     */ 
    public void shutdown() 
    { 
        shouldContinue = false; 
    } 

}