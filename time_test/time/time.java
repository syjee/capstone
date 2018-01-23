package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.net.ntp.TimeStamp;

public class time {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/** CalendarŬ������ �޷��� ǥ���� Ŭ���� - �߻� Ŭ����
		 * ���� �޼ҵ� getInstance�� �̿��ϸ� ���� OS�� �����Ǿ� �ִ� �ð��븦 �������� ��
		 * Calendar ���� ��ü�� ���� �� �ִ�. get�޼ҵ带 �̿��Ͽ� ��¥�� �ð� ������ �д´�.
		 * set�޼ҵ带 �̿��Ͽ� ������ ��¥�� �������� �� ���� �ִ�.
		 */
		
		System.out.println("#case 1");
		Calendar cal = Calendar.getInstance();
		
		//SimpleDataFormatŬ���� - ��¥/�ð� ����� ����ִ� ���� Ŭ����
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
		
		
		/**DateŬ���� - ������ ��¥�� �ð��� ǥ���ϴ� Ŭ����
		/*toString �޼ҵ带 �̿��Ͽ� ���ڿ��� ����
		 */
		
		System.out.println("\n#case 3");
		Date d = new Date();
		System.out.println(d.toString());
		
		/**currentTimeMillis �޼ҵ�
		*�ý��� �ð�� ���� ����ð��� ms������ �о�´�
		*DateŬ������ CalendarŬ������ ���� �ð��� �޾ƿ� �� ����ڰ� ���ϴ� ���·� �����ϸ鼭 ������ �߻�
		*�׷��Ƿ� �ð��� ������������ currenTimeMills �޼ҵ�� ���α׷� ���� ������ ���� 
		 */
		
		System.out.println("\n#case 4");
		Long c_time = System.currentTimeMillis();
		System.out.println("current_time(ms)= "+c_time);
		
	}

}
