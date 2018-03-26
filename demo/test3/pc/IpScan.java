import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;

public class IpScan implements Runnable{
	
	public int ip;
	public int timeout=900;
	public String subnet = "172.30.1";
	public String host = null;
	public DBHelper db;
	
	public IpScan(int ip){
		this.ip = ip;
		this.host = subnet + '.' + ip;
		this.db = new DBHelper();
	}
	
	public IpScan(String subnet, int ip){
		this.ip = ip;
		this.subnet = subnet;
		this.host = subnet + '.' + ip;
		this.db = new DBHelper();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			if(InetAddress.getByName(host).isReachable(timeout)){
				System.out.println("[IP]"+host+" is reachable");
				db.addtoFile(db.getIpListFile(), host);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Thread " + host +" detected error");
		}
	}
}
