import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class mkfile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String dbFolder = "DB";
		File dta = new File(dbFolder + "/blockchain");
		File drt = new File(dbFolder);
		
		if(!drt.exists())drt.mkdir();
		if(!dta.exists()) dta.createNewFile();
	
		// Adding contents on the file 
		FileWriter fw = new FileWriter(dta, true);
		for(int i = 1; i < 3; i++) {
			// If you use this way for next line, you have to add \r\n 
			String data = i + "line is inputed.\r\n";
			fw.write(data);
		}
		fw.close(); // close the fw2 object
		
		
		// Adding contents on the file 
		PrintWriter pw = new PrintWriter(new FileWriter(dta, true));
		for(int i = 3; i < 6; i++) {
			// If you use this way for next line, you have to use println method
			String data = i + "line is inputed.";
			pw.println(data);
		}
		pw.close(); // close the pw2 object
	
		// Read line by line on the file 
		BufferedReader br = new BufferedReader(new FileReader(dta));
		while(true) {			
			String line = br.readLine();
			if(line == null) 
				break;
			System.out.println(line);
		}
		br.close(); // close the br object
	}

}
