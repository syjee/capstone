import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		db.setDB();
		
		IpScanManager ism = new IpScanManager();
		ism.start();
	}

}
