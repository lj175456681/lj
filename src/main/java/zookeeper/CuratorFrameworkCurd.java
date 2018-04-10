package zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Title: CuratorFrameworkCurd
 * Description:
 * author: liujie
 * date: 2018-01-04 下午4:35
 */
public class CuratorFrameworkCurd {


    private static final String PATH = "curator";

    private RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);

    private CuratorFramework client;

    public CuratorFrameworkCurd() throws InterruptedException {
        client = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(5000)
                .namespace(PATH)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        //因为是异步链接的，这里阻塞等链接完成
        boolean connected = client.blockUntilConnected(5, TimeUnit.SECONDS);
        if(!connected){
            System.out.println("zk 链接失败");
        }
    }

    public void create(String path,String data) throws Exception {
        if(!exists(path)){
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(path,data.getBytes());
        }
    }

    public void delete(String path) throws Exception {
        client.delete().deletingChildrenIfNeeded().forPath(path);
    }

    public void set(String path,String data) throws Exception {
        client.setData().forPath(path,data.getBytes());
    }

    public List<String> getChildren(String path) throws Exception {
        return client.getChildren().forPath(path);
    }

    public boolean exists(String path) throws Exception {
        return null == client.checkExists().forPath(path);
    }

    public String getData(String path) throws Exception {
        Stat stat = new Stat();
        System.out.println(stat.toString());
        return new String(client.getData().storingStatIn(stat).forPath(path));
    }

    public CuratorFramework getClient() {
        return client;
    }

    public static void main(String[] args) throws Exception {
        CuratorFrameworkCurd test = new CuratorFrameworkCurd();
        /*test.create("/liujie","123");
        System.out.println(test.getData("/liujie"));
        test.create("/liujie/a/b","456");
        System.out.println(JSON.toJSONString(test.getChildren("/liujie")));
        test.set("/liujie","789");
        System.out.println(test.getData("/liujie"));*/
        test.delete("/liujie");
//        System.out.println(test.getChildren("/liujie"));
    }


}
