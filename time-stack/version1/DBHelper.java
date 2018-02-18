

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;

/** DBHelper class
 * DB폴더의 파일들을 관리하는 객체
 * 폴더 생성, 파일 생성, 파일반환, 파일업데이트 기능이 있다.
 * @author 소영
 */

public class DBHelper {
	private String dbFolder = "DB";
	private File Blockchain;
	private File IpList;
	private File PeerList;
	
	public DBHelper(){
		Blockchain = new File(dbFolder + "/Blockchain");
		IpList = new File(dbFolder + "/IpList.lst");
		PeerList = new File(dbFolder + "/PeerList.lst");
	}
	
	public void mkDBFolder(){
		File drt = new File(dbFolder);
		if(!drt.exists())drt.mkdir();
	}

	public void mkFile(File dta){
		dta.delete();
		if(!dta.exists())
			try {
				dta.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Creating a File detected an error...");
			}
	}

	public File getIpListFile(){
		return IpList;
	}
	
	public File getBlockchainFile(){
		return Blockchain;
	}
	
	public File getPeerListFile(){
		return PeerList;
	}

	public void updateFile(File dta){
		try {
			dta.delete();
			dta.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Updating a File detected an error...");
		}
	}
	public void addtoFile(File dta,String str){
		try {
			FileWriter fw = new FileWriter(dta, true);
			fw.write(str+'\n');
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Adding to File detected an error...");
		}
	}
	
	public void setDB(){
		mkDBFolder();
		mkFile(Blockchain);
		mkFile(IpList);
		mkFile(PeerList);
	}
}
