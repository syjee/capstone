
import java.io.File;
import java.io.IOException;

public class DBHelper {
	public String dbFolder = "DB";
	public File Blockchain;
	public File IpList;
	public File PeerList;
	
	public DBHelper(){
		Blockchain = new File(dbFolder + "/Blockchain");
		IpList = new File(dbFolder + "/IpList.lst");
		PeerList = new File(dbFolder + "/PeerList.lst");
	}
	
	public void mkDBFolder(){
		File drt = new File(dbFolder);
		if(!drt.exists())drt.mkdir();
	}

	public void mkBlockchainFile(){
		Blockchain = new File(dbFolder + "/Blockchain");
		if(!Blockchain.exists())
			try {
				Blockchain.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Creating a File detected an error...");
			}
	}
	
	public void mkIpListFile(){
		IpList = new File(dbFolder + "/IpList.lst");
		if(!IpList.exists())
			try {
				IpList.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Creating a File detected an error...");
			}
	}
	
	public void mkPeerListFile(){
		PeerList = new File(dbFolder + "/PeerList.lst");
		if(!PeerList.exists())
			try {
				PeerList.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Creating a File detected an error...");
			}
	}
	
	public File getIpListFile(){
		return IpList;
	}
	
	public void updateIpListFile(){
		IpList = new File(dbFolder + "/IpList.lst");
		try {
			IpList.delete();
			IpList.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Updating a File detected an error...");
		}
	}
	public void setDB(){
		mkDBFolder();
		mkIpListFile();
		mkPeerListFile();
		mkBlockchainFile();
	}
}
