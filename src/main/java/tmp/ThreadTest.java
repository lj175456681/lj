package tmp;

/**
 * Title: ThreadTest
 * Description:
 * author: liujie
 * date: 2018-01-11 上午10:20
 */
public class ThreadTest implements Runnable{


    @Override
    public void run() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程启动");
    }

    public static void main(String[] args) {
        Runnable a = new ThreadTest();
        a.run();
        System.out.println("123");
    }
}
