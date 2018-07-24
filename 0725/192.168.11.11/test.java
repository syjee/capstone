import java.text.SimpleDateFormat;
import java.util.Calendar;


public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Calendar cal;
		SimpleDateFormat form;
		
		cal = Calendar.getInstance();
		form = new SimpleDateFormat("hh:mm:ss");
		
		String time = "1529645929057";
		
		cal.setTimeInMillis(Long.parseLong(time));
		String str = form.format(cal.getTime());
		
		System.out.println(str);
		

		
		
		
		

	}

}
