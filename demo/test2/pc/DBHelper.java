

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

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
	
	public void deleteFromFile(File dta,String str){
		try {
			File tempFile = new File(dbFolder + "/temp.lst");
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			BufferedReader reader = new BufferedReader(new FileReader(dta));
			String currentLine;

			while((currentLine = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.equals(str)) continue;
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			
			writer.close(); 
			reader.close(); 
			if(tempFile.renameTo(dta)) System.out.println("Success to Rename a file");
			else System.out.println("Fail to Rename a file");
//			tempFile.delete();
			
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
