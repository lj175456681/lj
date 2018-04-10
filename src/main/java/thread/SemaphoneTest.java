package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Title: SemaphoneTest
 * Description:
 * author: liujie
 * date: 2017-08-03 下午3:28
 */
public class SemaphoneTest implements Runnable{

    private Semaphore semaphore;

    public SemaphoneTest(Semaphore semaphore){
        this.semaphore = semaphore;
    }


    @Override
    public void run() {
        try{
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5000L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService service  = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(5);
        for(int i = 0 ; i< 10 ;i++){
            service.execute(new SemaphoneTest(semaphore));
        }
        service.shutdown();
    }
}
