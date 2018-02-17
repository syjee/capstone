import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class threadPool extends ThreadPoolExecutor{
	
	// 무제한큐 LinkedBlockingQueue
	public threadPool(){
		super(50,100,100L,TimeUnit.SECONDS, new LinkedBlockingQueue<>());
	}
	
	public threadPool(int arg0, int arg1, long arg2, TimeUnit arg3, BlockingQueue<Runnable> arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
		// TODO Auto-generated constructor stub
	}
	
	//스레드가 정상적으로 또는 예외에 의해 종료된 후 런타임 라이브러리에의해 실행된다.
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		// TODO Auto-generated method stub
		super.afterExecute(r, t);
//		System.out.println("Thread terminated...");
	}
	
	//스레드를 실행하기전에 런타임 라이브러리에 의해 실행된다.
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		// TODO Auto-generated method stub
		super.beforeExecute(t, r);
//		System.out.println("Thread running...");
	}
	
	@Override
	protected void terminated() {
		// TODO Auto-generated method stub
		super.terminated();
	}
}
