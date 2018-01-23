package test;

import java.io.IOException;
import java.util.*;

public class RunTimeTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		/** Java Runtime Class - JVM�� �۵��ϴ� �ý����� OS���� �������̽��� �۵��ϴ� Ŭ����
		 * �ַ� �ü�� ����� ���α׷��� �����ϰų� ������ �������� ����� ����Ѵ�.
		 * �ý��� ħ���� �ֿ� ��ΰ� �� �� �����Ƿ� ����� ���ȿ�Ҹ� ����ؾ���.
		 */
		
		
		Runtime rt = Runtime.getRuntime();
		Process pc = null;
		
		Calendar cal = Calendar.getInstance();
				
		//1�� ������ �ý��� �ð� ����
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
			//��ɾ� ����� ���� ���
			pc.waitFor();
			//��ɾ� ����� ���� ���μ��� ����
			pc.destroy();
		}
	}

	private static String convertToTwoDigits(int number) {
		// TODO Auto-generated method stub
		return String.valueOf(number < 10 ? "0" + number : number);
	}

}
