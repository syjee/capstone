import java.net.*; 
import java.util.*; 
 
/** * This thread listens on a provided port (8015 by default) for incoming connections, and attempts to make connections to external peers based on guidance from MainClass. 
 * It needs a bit of help with memory management and resource deallocation, but otherwise it works. Good enough for 2.0.0a1. 
 *  
 * Future plans include some form of UPNP support and NAT punchthroughs. 
 */ 
public class RPC extends Thread 
{ 
    public int listenPort; 
    public boolean shouldRun = true; 
    public ArrayList<RPCThread> rpcThreads; 
    public PeerNetworkManager pm;
    public KeyManager km;
    public myIP mip;
    public Blockchain bc;
    
    public BlockNetworkManager bnm;
    public ServerSocket listenSocket;
    /**     * Default settings constructor 
     */ 
    public RPC() 
    { 
        this.listenPort = 8016; 
        this.rpcThreads = new ArrayList<RPCThread>();  
    } 
    
    public RPC(PeerNetworkManager pm,Blockchain bc,KeyManager km,BlockNetworkManager bnm) 
    { 
        this.listenPort = 8016; 
        this.rpcThreads = new ArrayList<RPCThread>();
        this.pm = pm;
        this.mip = new myIP("wlan0");
        this.km = km;
        this.bc = bc;
        this.bnm = bnm;
    }
    
    
    /**     * Attempts a connection to an external peer 
     *  
     * @param peer Peer to connect to 
     * @param port Port on peer to connect to 
     */ 
    public String getServerSocketIp(ServerSocket listenSocket) {
    	String remoteHost = listenSocket.getInetAddress() + ""; 
        remoteHost = remoteHost.replace("/", ""); 
        remoteHost = remoteHost.replace("\\", ""); 
        
        System.out.println("[RPC]ServerSocket ip = "+remoteHost);
        
        return "192.168.11.100";
    }
    
    public boolean isSelf(InetAddress ip){
    	boolean isMe = false;
		try {
			ip = InetAddress.getLocalHost();
			if(ip.isLoopbackAddress() || ip.isSiteLocalAddress()){
	        	isMe = true;
			}
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
            	rpcThreads.add(new RPCThread(socket,pm,bc,km,bnm)); 
               	rpcThreads.get(rpcThreads.size() - 1).start();
            } 
        
        } catch (Exception e) 
        { 
            System.out.println("[RPC]Unable to connect to " + peer + ":" + port); 
        } 
    } 
 
    /**     * Optional, currently-unused constructor for a non-default port selection 
     *  
     * @param port Port to listen on 
     */ 
    public RPC(int port) 
    { 
        this.listenPort = port; 
        this.rpcThreads = new ArrayList<RPCThread>();
    } 
 
    /**     * Runs as a separate thread, constantly listening for peer connections. 
     */ 
    public void run() 
    { 
        try 
        { 
        	listenSocket = new ServerSocket(listenPort); 
  //      	ArrayList<Socket> cSocket = new ArrayList<Socket>();
            while (shouldRun) //Doesn't actually quit right when shouldRun is changed, as while loop is pending. 
            { 
//            	cSocket.add(listenSocket.accept());
//            	if(!isSelf(cSocket.get(cSocket.size()-1).getInetAddress())) {
            		rpcThreads.add(new RPCThread(listenSocket.accept(),pm,bc,km,bnm)); 
            		rpcThreads.get(rpcThreads.size() - 1).start();
 //               }
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
    
    public void request(){
    	/*
    	Transaction newTransaction = new Transaction();
    	for (int i = 0; i < rpcThreads.size(); i++) 
        { 
 //           System.out.println("Sent Transaction:: " + newTransaction.getTransaction()); 
            rpcThreads.get(i).send(newTransaction); 
        }
    	*/
    	
    }
    
    public void request(int difficulty) throws Exception{
    	Transaction newTransaction = new Transaction(difficulty,km);
    	Block newBlock = new Block(newTransaction);
    	for (int i = 0; i < rpcThreads.size(); i++) 
        { 
  //          System.out.println("Sent Transaction:: " + newTransaction.getTransaction());
            rpcThreads.get(i).send(newBlock); 
        }
    }
    
    public void dRequest(int difficulty) throws Exception{
    	/*
    	Transaction newTransaction;
    	for (int i = 0; i < rpcThreads.size(); i++) 
        { 
  //          System.out.println("Sent Transaction:: " + newTransaction.getTransaction());
    		newTransaction = new Transaction(difficulty,km,rpcThreads.get(i).getSocket().getInetAddress().toString());
            rpcThreads.get(i).send(newTransaction); 
        }
    	*/
    }
    
    public void request(Block newBlock){
    	for (int i = 0; i < rpcThreads.size(); i++) 
        { 
  //          System.out.println("Sent Transaction:: " + newTransaction.getTransaction());
            rpcThreads.get(i).send(newBlock); 
        }
    }
   
    /**     * Announces the same message to all peers except the ignored one simultaneously. Useful when re-broadcasting messages.  
     * Peer ignored as it's the peer that sent you info.  
     *  
     * @param toBroadcast String to broadcast to peers 
     * @param peerToIgnore Peer to not send broadcast too--usually the peer who sent information that is being rebroadcast 
     */ 
   
}

