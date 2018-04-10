package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Title: FutureTest
 * Description:
 * author: liujie
 * date: 2017-08-25 下午2:47
 */
public class FutureTest {



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<String> future = service.submit(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        Thread.sleep(5000L);
                        return "hello world";
                    }
                }
        );
        service.shutdown();
        Thread.sleep(2000L);
        future.cancel(true);
        System.out.println(future.get());
    }
}
