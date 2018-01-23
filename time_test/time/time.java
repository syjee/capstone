package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.net.ntp.TimeStamp;

public class time {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/** Calendar클래스는 달력을 표현한 클래스 - 추상 클래스
		 * 정적 메소드 getInstance를 이용하면 현재 OS에 설정되어 있는 시간대를 기준으로 한
		 * Calendar 하위 객체를 얻을 수 있다. get메소드를 이용하여 날짜와 시간 정보를 읽는다.
		 * set메소드를 이용하여 셋팅한 날짜를 기준으로 할 수도 있다.
		 */
		
		System.out.println("#case 1");
		Calendar cal = Calendar.getInstance();
		
		//SimpleDataFormat클래스 - 날짜/시간 모양을 잡아주는 포맷 클래스
		SimpleDateFormat form = new SimpleDateFormat("hh-mm-ss");
		
		String str = form.format(cal.getTime());
		System.out.println(str);
		
		
		System.out.println("\n#case 2");
		TimeStamp now = TimeStamp.getCurrentTime();
		Long timeValue = now.getTime();
		Date dateValue = now.getDate();
		
		String time = (timeValue == null) ? "N/A" : timeValue.toString();
		int h = dateValue.getHours();
		int m = dateValue.getMinutes();
		int s = dateValue.getSeconds();
		
		System.out.println(h +"-"+ m +"-"+ s);
		System.out.println("current_time(ms)= "+time);
		
		
		/**Date클래스 - 현재의 날짜와 시간을 표현하는 클래스
		/*toString 메소드를 이용하여 문자열로 리턴
		 */
		
		System.out.println("\n#case 3");
		Date d = new Date();
		System.out.println(d.toString());
		
		/**currentTimeMillis 메소드
		*시스템 시계로 부터 현재시간을 ms단위로 읽어온다
		*Date클래스나 Calendar클래스는 현재 시간을 받아온 뒤 사용자가 원하는 형태로 가공하면서 딜레이 발생
		*그러므로 시간을 직접가져오는 currenTimeMills 메소드는 프로그램 성능 측정에 사용됨 
		 */
		
		System.out.println("\n#case 4");
		Long c_time = System.currentTimeMillis();
		System.out.println("current_time(ms)= "+c_time);
		
	}

}
