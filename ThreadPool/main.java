import java.util.concurrent.*;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int threadCounter = 0;
		threadPool pool = new threadPool();
		
		/* newFixedThreadPool �̳� newSingleThreadExecutor �� ũ�Ⱑ ���ѵ��� ���� LinkedBlockingQueue�� ����Ѵ�.  
		 * �����尡 ��� ������̴��� �۾���  executor() �� �� �ִ�.
		 * ������ ũ�Ⱑ ���ѵ� newCachedThreadPool�� SynchronousQueue�� ����Ͽ� �����尡 �ִ밪�� �ٴٸ��� ����  executor() �� �۾��� �ź��ϰ� �ȴ�.
		 * ���ѵ� ť�� �ڿ���뷮�� ������ �� ������ ť�� �������� �۾��� �߰� ���� �ʵ��� �������־�� �ȴ�.
		 * ť�� ����á�� ��� ���������� setRejectedExecutionhandler �� ����Ѵ�.
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
