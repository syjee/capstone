import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeSyncronization {
	public Runtime rt;
	public Process pc;//=null;
	public Calendar cal;
	public SimpleDateFormat form;
	
	public TimeSyncronization(){
		cal = Calendar.getInstance();
		form = new SimpleDateFormat("HH:mm:ss");
		rt = Runtime.getRuntime();
	}
	public void StringDateSync(String str) {

		try {
//			pc = rt.exec("sudo date -s " + str);
			pc = rt.exec(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("dateSync() error...");
		}

		System.out.println(str + " dateSync() ...");

	}

	public void StringTimeSync(String str) {
		try {
			pc = rt.exec("sudo date -s " + str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("timeSync() error...");
		}

		System.out.println(str + " timeSync() ...");

	}

	public void timeSync(long timeStr){
		try {
			cal.setTimeInMillis(timeStr);
			String str = form.format(cal.getTime());
			pc = rt.exec("sudo date -s "+str);

			System.out.println("[TIMESYNC]"+timeStr);
			System.out.println("[TIMESYNC]"+str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("[TIMESYNC]ERROR...");
		}
	}
	
}