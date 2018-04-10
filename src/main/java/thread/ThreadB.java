package thread;

/**
 * Title: ThreadB
 * Description:
 * author: liujie
 * date: 2017-06-21 下午2:47
 */
public class ThreadB extends Thread {
    private Service service;

    public ThreadB(Service service){
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.methodB();
    }
}
