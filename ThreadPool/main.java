import java.util.concurrent.*;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int threadCounter = 0;
		threadPool pool = new threadPool();
		
		/* newFixedThreadPool 이나 newSingleThreadExecutor 는 크기가 제한되지 않은 LinkedBlockingQueue를 사용한다.  
		 * 쓰레드가 모두 사용중이더라도 작업을  executor() 할 수 있다.
		 * 하지만 크기가 제한된 newCachedThreadPool은 SynchronousQueue를 사용하여 스레드가 최대값에 다다르면 이후  executor() 된 작업은 거부하게 된다.
		 * 제한된 큐는 자원사용량을 제한할 수 잇지만 큐가 가득차면 작업이 추가 되지 않도록 조절해주어야 된다.
		 * 큐가 가득찼을 경우 대응설정은 setRejectedExecutionhandler 를 사용한다.
		 */ 
		
		/*
		pool.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r,
                    ThreadPoolExecutor executor) {
                System.out.println("DemoTask Rejected : "
                        + ((test) r).getName());
                System.out.println("Waiting for a second !!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Lets add another time : "
                        + ((test) r).getName());
                executor.execute(r);
            }
        });
		*/
		
		pool.prestartAllCoreThreads();
		
		while (true) {
            threadCounter++;
            pool.execute(new IpScan(threadCounter));
            if (threadCounter == 255)
                break;
        }
		
		pool.shutdown();
	}

}
