package test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeSyncronization {
	public Runtime rt;
	public Process pc = null;
	public Calendar cal;
	public SimpleDateFormat form;
	
	public TimeSyncronization(){
		cal = Calendar.getInstance();
		form = new SimpleDateFormat("hh:mm:ss");
		rt = Runtime.getRuntime();
	}
	
	public void timeSync(Long timeStr){
		try {
			cal.setTimeInMillis(timeStr);
			String str = form.format(cal.getTime());
			pc = rt.exec("cmd /c time "+str);

			System.out.println(str + " timeSync() ...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("timeSync() error...");
		}
	}
	
}
