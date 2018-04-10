package netty.server;

/**
 * Title: ServerMain
 * Description:
 * author: liujie
 * date: 2017-08-16 下午5:47
 */
public class ServerMain {

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        new TimeServer().bind(port);
    }
}
