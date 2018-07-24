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
		form = new SimpleDateFormat("HH:mm:ss.SS");
		rt = Runtime.getRuntime();
	}
	
	
	public void timeSync(Long timeLong){
		try {
			cal.setTimeInMillis(timeLong);
			String str = form.format(cal.getTime());
//			pc = rt.exec("cmd /c time "+str);
			pc = rt.exec("cmd /c time "+str.substring(0,11));

//			System.out.println(str + " timeSync() ...");
			System.out.println(str.substring(0,11) + " timeSync() ...");
			System.out.println(timeLong + " timeSync() ...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("timeSync() error...");
		}
	}

	public void StringTimeSync(String str) {
		try {
			pc = rt.exec("cmd /c time "+str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("timeSync() error...");
		}

		System.out.println(str + " timeSync() ...");

	}
	public void timeSyncAdd(Long timeStr){
		try {
			cal.setTimeInMillis(timeStr);
			
			String str = form.format(cal.getTime());
			str+="."+timeStr.toString().substring(11, 13);
			pc = rt.exec("cmd /c time "+str);

			System.out.println(str.substring(0,11) + " timeSync() ...");
			
			System.out.println(timeStr + " timeSync() ...");
			
			System.out.println("offset : "+timeStr.toString().substring(11, 13));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("timeSync() error...");
		}
	}
	
	public void timeSync(Long timeStr,int hour,int min, int sec){
		try {
			cal.setTimeInMillis(timeStr);
	//		cal.add(Calendar.HOUR, 0);
	//		cal.add(Calendar.MINUTE, 0);
	//		cal.add(Calendar.SECOND, 0);
			cal.add(Calendar.MILLISECOND, 0);
			String str = form.format(cal.getTime());
			
			
			pc = rt.exec("cmd /c time "+str.substring(0,11));
			
			long secs = cal.getTimeInMillis();
			System.out.println("[TIME]"+str.substring(0,11) + " timeSync() ...");
			System.out.println("[TIME]"+secs + " timeSync() ...");
			
			
			

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("[TIME]timeSync() error...");
			e.printStackTrace();
		}
	}
	
}