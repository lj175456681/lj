package tmp;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Title: ThreadPoolExecutorTest
 * Description:
 * author: liujie
 * date: 2018-03-27 下午5:09
 */
public class ThreadPoolExecutorTest {


    public static void main(String[] args) {

        class MyThread extends Thread{
            private String name;
            public MyThread(String name){
                this.name = name;
            }

            @Override
            public void run() {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + ": over");
                super.run();
            }
        }

        ThreadPoolExecutor pool =  new ThreadPoolExecutor(2,4,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3));
        try{
            for(int i = 0 ; i<10 ;i++){
                MyThread thread = new MyThread(i+"");
                pool.execute(thread);
            }
        }finally {
            pool.shutdown();
        }
    }




}
