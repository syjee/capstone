import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;

public class IpScan implements Runnable{
	
	public int ip;
	public int timeout=900;
	public String subnet = "172.30.1";
	public String host = null;
	
	public IpScan(int ip){
		this.ip = ip;
		this.host = subnet + '.' + ip;	
	}
	
	public IpScan(String subnet, int ip){
		this.ip = ip;
		this.subnet = subnet;
		this.host = subnet + '.' + ip;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			File dta = new File("IpList.lst");
			FileWriter fw = new FileWriter(dta, true);
			if(!dta.exists()) dta.createNewFile();
			
			if(InetAddress.getByName(host).isReachable(timeout)){
				System.out.println(host+" is reachable");
				fw.write(host+'\n');
			}
			
			fw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Thread " + host +" detected error");
		}
	}
}
