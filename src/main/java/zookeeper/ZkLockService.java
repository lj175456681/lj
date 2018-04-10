package zookeeper;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Title: ZkLockService
 * Description:
 * author: liujie
 * date: 2018-01-05 下午5:44
 */
public class ZkLockService extends CuratorFrameworkCurd{

    private final InterProcessMutex lock;

    public ZkLockService(String lockPath) throws InterruptedException {
        super();
        lock = new InterProcessMutex(getClient(),lockPath);
    }

    public boolean lock(){
        boolean result = true;
        try{
            lock.acquire();
        }catch(Exception e){
            result = false;
        }
        return result;
    }

    public void lock(long time , TimeUnit unit) throws Exception {
        lock.acquire(time,unit);
    }

    public void release(){
        try{
            lock.release();
        }catch(Exception e){

        }
    }

    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(10);
        ZkLockService service = new ZkLockService("/lock");
        for(int i = 0 ; i < 10 ; i++){
            new Thread(
                    ()->{
                        try{
                            service.lock();
                            SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss|SSS");
                            String orderNo = sf.format(new Date());
                            System.out.println(orderNo);
                        } finally {
                            service.release();
                            latch.countDown();
                        }
                    }
            ).start();
        }
        latch.await();
        service.delete("/lock");
    }


}
