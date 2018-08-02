import java.text.SimpleDateFormat;
import java.util.Calendar;


public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		myIP mip = new myIP("wlan0");
		System.out.println(mip.getMyIP());
		
		
		
		String[] arr =mip.getMyIP().replace(".", ",").split(","); 
		System.out.println(arr[0]+"."+arr[1]+"."+arr[2]+".0");
		
	}

}
