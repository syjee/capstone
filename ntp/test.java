import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;

import java.util.Calendar;

import com.sun.jna.*;
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WindowsSetSystemTime ws = new WindowsSetSystemTime();
		Calendar cal = Calendar.getInstance();
		ws.SetLocalTime(Short.parseShort("2018"), Short.parseShort("7"), Short.parseShort("24"),
				Short.parseShort("15"), Short.parseShort("30"), Short.parseShort("1"));
		
//		SYSTEMTIME sys = new SYSTEMTIME();
//		System.out.println(sys.wDay);
		
	}

}
