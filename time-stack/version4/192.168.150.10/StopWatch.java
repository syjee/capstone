import java.io.IOException;

public class StopWatch extends Thread{
	static String timerBuffer; // 04:11:15 등의 경과 시간 문자열이 저장될 버퍼 정의
	static int oldTime; // 타이머가 ON 되었을 때의 시각을 기억하고 있는 변수
	
	public void run() {
		stopwatch(1);
	}
	
	public void stopwatch(int onOff) {
		if (onOff == 1) // 타이머 켜기
			oldTime = (int) System.currentTimeMillis() / 1000;

		if (onOff == 0) // 타이머 끄고, 시분초를 timerBuffer 에 저장
			secToHHMMSS(((int) System.currentTimeMillis() / 1000) - oldTime);

	}

	// 정수로 된 시간을 초단위(sec)로 입력 받아, "04:11:15" 등의 형식의 문자열로 시분초를 저장
	public void secToHHMMSS(int secs) {
		int hour, min, sec;

		sec = secs % 60;
		min = secs / 60 % 60;
		hour = secs / 3600;

		timerBuffer = String.format("%02d:%02d:%02d", hour, min, sec);
		System.out.format("Timer OFF! 경과 시간: [%s]%n", timerBuffer); // 경과 시간 화면에 출력
	}

	public void pause() {
		stopwatch(0);
		this.stop();
	}
}
