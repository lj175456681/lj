package thread;

/**
 * Title: TestMain
 * Description:
 * author: liujie
 * date: 2017-06-21 下午2:48
 */
public class TestMain {
    public static void main(String[] args) throws InterruptedException {
        Service service  = new Service();
        ThreadA a = new ThreadA(service);
        ThreadB b = new ThreadB(service);
        a.start();
        Thread.sleep(100);
        b.start();
    }
}
