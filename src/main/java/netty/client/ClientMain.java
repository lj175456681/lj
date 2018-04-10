package netty.client;

/**
 * Title: ClientMain
 * Description:
 * author: liujie
 * date: 2017-08-17 上午10:23
 */
public class ClientMain {

    public static void main(String[] args) throws InterruptedException {
        new TimeClient().connect(8080,"127.0.0.1");
    }
}
