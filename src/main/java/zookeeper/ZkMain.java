package zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;


/**
 * Title: ZkMain
 * Description:
 * author: liujie
 * date: 2018-01-04 下午1:16
 */
public class ZkMain {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        Watcher watcher = new MyWatcher();
        ZooKeeper zk = new ZooKeeper("localhost:2181/zk",5000,watcher);
        List<String> list = zk.getChildren("/",watcher);
        System.out.println(JSON.toJSONString(list));
//        zk.create("/zk","liujie".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE , CreateMode.PERSISTENT);
//        zk.create("/zk/lchild","zhx".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE ,CreateMode.EPHEMERAL);
//        zk.create("/zk/rchild","yb".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE ,CreateMode.EPHEMERAL);
//        List<String>childrenList = zk.getChildren("/zk",true);
//        System.out.println(JSON.toJSONString(childrenList));
        Thread.sleep(Integer.MAX_VALUE);
    }
}
