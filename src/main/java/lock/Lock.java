package lock;

/**
 * Title: Lock
 * Description:
 * author: liujie
 * date: 2018-01-08 下午4:50
 */
public interface Lock {

    /**
     * 获取锁
     * @param key
     * @return
     */
    boolean require(String key);


    /**
     * 获取锁
     * @param key
     * @param expireTime 超时时间
     * @return
     */
    boolean require(String key,long expireTime);

    /**
     * 释放锁
     * @param key
     */
    void release(String key);
}
