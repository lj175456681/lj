package thread;

/**
 * Title: DaemonTest
 * Description:
 * author: liujie
 * date: 2017-07-26 下午4:32
 */
public class DaemonTest {

    public static void main(String[] args) throws InterruptedException {
        MyThread threadOne = new MyThread("守护线程");
        threadOne.start();
        System.out.println(" main end ");
    }
}
