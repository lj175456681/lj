package thread;

/**
 * Title: MyThread
 * Description:
 * author: liujie
 * date: 2017-07-26 下午4:28
 */
public class MyThread extends Thread {



    public MyThread(String name){
        this.setName(name);
        this.setDaemon(true);
    }

    @Override
    public void run() {
        System.out.println("Thread name : " + this.getName() + ", is started");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread name : " + this.getName() + ", is end");
    }
}
