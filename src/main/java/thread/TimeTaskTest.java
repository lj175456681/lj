package thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Title: TimeTaskTest
 * Description:
 * author: liujie
 * date: 2017-05-15 上午9:50
 */
public class TimeTaskTest {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("abc");
                timer.cancel();
            }
        }, 5000);
        timer.purge();
    }
}
