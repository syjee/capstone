package test;

import java.io.IOException;
import java.util.*;

public class RunTimeTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		/** Java Runtime Class - JVM이 작동하는 시스템의 OS와의 인터페이스로 작동하는 클래스
		 * 주로 운영체제 기반의 프로그램을 실행하거나 정보를 가져오는 기능을 사용한다.
		 * 시스템 침입의 주요 경로가 될 수 있으므로 실행시 보안요소를 고려해야함.
		 */
		
		
		Runtime rt = Runtime.getRuntime();
		Process pc = null;
		
		Calendar cal = Calendar.getInstance();
				
		//1분 전으로 시스템 시간 변경
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE)-1;
		int second = cal.get(Calendar.SECOND);

		String timeStr = convertToTwoDigits(hour) + ":"
			    + convertToTwoDigits(minute) + ":" + convertToTwoDigits(second);
			  System.out.println("timeStr: " + timeStr);
		
		try {
			pc = rt.exec("cmd /c time "+timeStr);
			System.out.println("\nCommand Excute\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//명령어 종료시 까지 대기
			pc.waitFor();
			//명령어 종료시 하위 프로세스 제거
			pc.destroy();
		}
	}

	private static String convertToTwoDigits(int number) {
		// TODO Auto-generated method stub
		return String.valueOf(number < 10 ? "0" + number : number);
	}

}
