package zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Title: MyWatcher
 * Description:
 * author: liujie
 * date: 2018-01-04 上午11:13
 */
public class MyWatcher implements Watcher{

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event.getPath());

        Event.KeeperState keeperState = event.getState();

        System.out.println("keeperState = " + keeperState.getIntValue());

        Event.EventType eventType = event.getType();
        switch (eventType){
            case None :
                System.out.println("啥也没发生");
                break;
            case NodeCreated :
                System.out.println("新增了一个节点");
                break;
            case NodeDeleted :
                System.out.println("删除了一个节点");
                break;
            case NodeDataChanged :
                System.out.println("节点值被修改");
                break;
            case NodeChildrenChanged :
                System.out.println("子节点有变动");
                break;
            default:
                System.out.println("未监听到事件的发生");
        }
    }
}
