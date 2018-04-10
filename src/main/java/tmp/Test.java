package tmp;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Title: Test
 * Description:
 * author: liujie
 * date: 2017-02-28 下午4:48
 */
public class Test {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.writeLock();
        readWriteLock.readLock();
        lock.lock();
        try{
            lock.newCondition();
        }finally {
            lock.unlock();
        }
    }
}
