import java.net.*; 
import java.util.*; 
 
/** * This thread listens on a provided port (8015 by default) for incoming connections, and attempts to make connections to external peers based on guidance from MainClass. 
 * It needs a bit of help with memory management and resource deallocation, but otherwise it works. Good enough for 2.0.0a1. 
 *  
 * Future plans include some form of UPNP support and NAT punchthroughs. 
 */ 
public class PeerNetwork extends Thread 
{ 
    public int listenPort; 
    public boolean shouldRun = true; 
    public ArrayList<PeerThread> peerThreads; 
    public ArrayList<String> newPeers; 
    public DBHelper db;
    public ServerSocket listenSocket;
    
    public myIP mip;
    
    public Blockchain bc;
    /**     * Default settings constructor 
     */ 
    public PeerNetwork(Blockchain bc) 
    { 
        this.listenPort = 8015; 
        this.peerThreads = new ArrayList<PeerThread>();  
        this.newPeers = new ArrayList<String>();
        this.bc = bc;
        this.db = new DBHelper();
        this.mip = new myIP("wlan0");
    } 
 
    /**     * Attempts a connection to an external peer 
     *  
     * @param peer Peer to connect to 
     * @param port Port on peer to connect to 
     * @throws UnknownHostException 
     */ 
    public String getServerSocketIp(ServerSocket listenSocket) throws UnknownHostException {
    	String remoteHost = listenSocket.toString() + ""; 
 //       remoteHost = remoteHost.replace("/", ""); 
  //      remoteHost = remoteHost.replace("\\", ""); 
    	InetAddress ip = InetAddress.getLocalHost();
    	System.out.println(ip.isLoopbackAddress());
    	System.out.println(ip.isAnyLocalAddress());
    	System.out.println(ip.isLinkLocalAddress());
    	System.out.println(ip.isSiteLocalAddress());
		System.out.println("Current IP address : " + ip.getHostAddress());
        System.out.println("[Peer]ServerSocket ip = "+remoteHost);
        
        return ip.getHostAddress().toString();
    }
    
    public boolean isSelf(InetAddress ip){
    	boolean isMe = false;
		try {
			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());
			if(ip.isLoopbackAddress()) {
				System.out.println(ip.getHostAddress());
	        	isMe = true;
			}
			else if(ip.isSiteLocalAddress()) {
				System.out.println(ip.getHostAddress());
	        	isMe = true;
			}
			else System.out.println("It's not me : "+ip.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return isMe;
    }
    
    public boolean isSelf(String peer){
    	boolean isMe = false;
		String ip = mip.getMyIP();
		if(ip.equals(peer))
			isMe = true;
        return isMe;
    }
    
    public void connectToPeer(String peer, int port) 
    { 
        try 
        {   
        	if(!isSelf(peer)) {
        		Socket socket = new Socket(peer, port);
            	peerThreads.add(new PeerThread(socket,bc)); 
               	peerThreads.get(peerThreads.size() - 1).start();
            }
            	
        } catch (Exception e) 
        { 
            System.out.println("[Peer]Unable to connect to " + peer + ":" + port); 
        } 
    } 
 
    /**     * Optional, currently-unused constructor for a non-default port selection 
     *  
     * @param port Port to listen on 
     */ 
    public PeerNetwork(int port) 
    { 
        this.listenPort = port; 
        this.peerThreads = new ArrayList<PeerThread>();
        this.newPeers = new ArrayList<String>();
    } 
 
    /**     * Runs as a separate thread, constantly listening for peer connections. 
     */ 
    public void run() 
    { 
        try 
        { 
            listenSocket = new ServerSocket(listenPort); 
 //           ArrayList<Socket> cSocket = new ArrayList<Socket>();
            while (shouldRun) //Doesn't actually quit right when shouldRun is changed, as while loop is pending. 
            { 
//            	cSocket.add(listenSocket.accept());
 //           	if(!isSelf(cSocket.get(cSocket.size()-1).getInetAddress())) {
            		peerThreads.add(new PeerThread(listenSocket.accept(),bc)); 
            		peerThreads.get(peerThreads.size() - 1).start();
            		
//            		System.out.println("[Peer]Got connection from " + cSocket.get(cSocket.size()-1).getInetAddress() + " : "+cSocket.get(cSocket.size()-1).getPort());
            		
//                }
            } 
            listenSocket.close(); 
        } catch (Exception e) 
        { 
            //e.printStackTrace(); //Most likely tripped by the inability to bind the listenPort.
            System.out.println("ServerSocket accept error...");
        } 
    } 
 
    /**     * Announces the same message to all peers simultaneously. Useful when re-broadcasting messages.  
     *  
     * @param toBroadcast String to broadcast to peers 
     */ 
    
    public void broadcast(Block newBlock){
    	for (int i = 0; i < peerThreads.size(); i++) 
        { 
//            System.out.println("Sent Block:: " + newBlock.getCurrentHash()); 
            peerThreads.get(i).send(newBlock); 
        }
    }

    /**     * Announces the same message to all peers except the ignored one simultaneously. Useful when re-broadcasting messages.  
     * Peer ignored as it's the peer that sent you info.  
     *  
     * @param toBroadcast String to broadcast to peers 
     * @param peerToIgnore Peer to not send broadcast too--usually the peer who sent information that is being rebroadcast 
     */ 
   
}
