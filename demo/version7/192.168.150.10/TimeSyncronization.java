
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeSyncronization {
	public Runtime rt;
	public Process pc = null;
	public Calendar cal;
	public SimpleDateFormat form;
	public DBHelper db;
	
	public TimeSyncronization(){
		cal = Calendar.getInstance();
		form = new SimpleDateFormat("hh:mm:ss");
		rt = Runtime.getRuntime();
		db = new DBHelper();
	}
	
	public void timeSync(Long timeStr,int hour,int min, int sec){
		try {
			cal.setTimeInMillis(timeStr);
			cal.add(Calendar.HOUR, hour);
			cal.add(Calendar.MINUTE, min);
			cal.add(Calendar.SECOND, sec);
			String str = form.format(cal.getTime());
			System.out.println("[TIME]"+str + " timeSync() ...");
			
			
			pc = rt.exec("sudo date -s "+str);
			
			db.addtoFile(db.getHistoryFile(), "Time Sync : "+cal.getTime());

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("[TIME]timeSync() error...");
			e.printStackTrace();
		}
	}
	
}

