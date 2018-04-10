package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Title: LockTest
 * Description:
 * author: liujie
 * date: 2018-02-27 下午2:35
 */
public class LockTest {

    private static Lock lock = new ReentrantLock();

    private static Condition conditionOne = lock.newCondition();

    private static Condition conditionTwo = lock.newCondition();


    public static void main(String[] args) throws InterruptedException {
        MyThreadOne one = new MyThreadOne();
        MyThreadTwo two = new MyThreadTwo();


        two.start();

        Thread.sleep(1000L);

        one.start();
    }

    static class MyThreadOne extends Thread{
        @Override
        public void run() {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName() + " 开始执行One");
                conditionOne.signal();
                System.out.println(Thread.currentThread().getName() + " 执行完毕One");
            } finally{
                lock.unlock();
            }
        }
    }

    static class MyThreadTwo extends Thread{

        @Override
        public void run() {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName() + " 开始执行Two");
                conditionOne.await();
                System.out.println(Thread.currentThread().getName() + " 执行完毕Two");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
