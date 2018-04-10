package thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Title: Service
 * Description:
 * author: liujie
 * date: 2017-06-21 下午2:44
 */
public class Service {

    private Lock lock = new ReentrantLock();

    private Condition condition  = lock.newCondition();

    private volatile boolean flag = true;

    private volatile AtomicInteger ai = new AtomicInteger(0);

    public void methodA(){
        lock.tryLock();
        try{
            while(ai.get() < 10)
                if(flag){
                    System.out.println("A");
                    flag = false;
                    ai.getAndIncrement();
                    condition.signal();
                }else{
                    condition.await();
                }
            } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void methodB(){
        lock.tryLock();
        try{
            while(ai.get() < 10)
                if(!flag){
                    System.out.println("B");
                    flag = true;
                    ai.getAndIncrement();
                    condition.signal();
                }else{
                    condition.await();
                }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
