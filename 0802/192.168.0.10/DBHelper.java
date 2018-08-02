

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	private File RPCList;
	private File History;
	
	public DBHelper(){
		Blockchain = new File(dbFolder + "/Blockchain.lst");
		IpList = new File(dbFolder + "/IpList.lst");
		PeerList = new File(dbFolder + "/PeerList.lst");
		RPCList = new File(dbFolder + "/RPCList.lst");
		History = new File(dbFolder + "/History.lst");
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
				System.out.println("Created a File!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Creating a File detected an error...");
			}
	}

	public synchronized File getHistoryFile(){
		return History;
	}
	public synchronized File getIpListFile(){
		return IpList;
	}
	
	public synchronized File getRPCListFile(){
		return RPCList;
	}
	
	public synchronized File getBlockchainFile(){
		return Blockchain;
	}
	
	public synchronized File getPeerListFile(){
		return PeerList;
	}

	public synchronized void updateFile(File dta){
		try {
			dta.delete();
			dta.createNewFile();
			System.out.println("Updated a File!!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Updating a File detected an error...");
		}
	}
	public synchronized void addtoFile(File dta,String str){
		try {
			FileWriter fw = new FileWriter(dta, true);
			fw.write(str+'\n');
			System.out.println("Added str to the File!!");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Adding to File detected an error...");
		}
	}
	
	public synchronized void deleteLine(File dta,String str) {
	    try
        {
                BufferedReader file = new BufferedReader(new FileReader(dta));
                String line;
                String input = "";
                while ((line = file.readLine()) != null) 
                {
                    //System.out.println(line);
                    if (line.contains(str))
                    {
                        System.out.println("[DB]Success to delete a Line : "+str);
                    }
                    else
                    	input += line + '\n';
                }
                FileOutputStream File = new FileOutputStream(dta);
                File.write(input.getBytes());
                file.close();
                File.close();
        }
        catch (Exception e)
        {
                System.out.println("[DB]Fail to delete a line : "+str);
        }
	}
	
	public synchronized boolean integrityCheck(File dta, String str) {
		boolean check = false;
		Scanner scan;
		String next;
		try {
			scan = new Scanner(dta);
			while (scan.hasNextLine()) 
	        { 
	            next = scan.nextLine(); 
	            if(next.equals(str)) {
	            	check = true;
	            	break;
	            }
	        }
			scan.close(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("integrityCheck fail...");
		}
		return check;
	}
	
	public void setDB(){
		System.out.println("Setted DB!!!");
		mkDBFolder();
		mkFile(Blockchain);
		mkFile(IpList);
		mkFile(PeerList);
		mkFile(RPCList);
		mkFile(History);
	}
}
