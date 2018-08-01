

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

public class DB {
	private String dbFolder = "DB";
	private File History;
	
	public DB(){
		
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Creating a File detected an error...");
			}
	}

	public synchronized File getHistoryFile(){
		return History;
	}
	
	public synchronized void updateFile(File dta){
		try {
			dta.delete();
			dta.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Updating a File detected an error...");
		}
	}
	public synchronized void addtoFile(File dta,String str){
		try {
			FileWriter fw = new FileWriter(dta, true);
			fw.write(str+'\n');
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
	
	public void setDB(){
		mkDBFolder();
		mkFile(History);
	}
}
