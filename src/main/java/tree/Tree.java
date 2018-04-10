package tree;

/**
 * Title: Tree
 * Description:
 * author: liujie
 * date: 2017-12-15 下午1:54
 */
public interface Tree<T extends Comparable> {

    /**
     * 是否为空数
     * @return
     */
    boolean isEmpty();

    /**
     * 节点个数
     * @return
     */
    int size();

    /**
     * 前序遍历
     * @return
     */
    void preOrder();

    /**
     * 中序遍历
     * @return
     */
    void midOrder();

    /**
     * 后续遍历
     * @return
     */
    void postOrder();

    /**
     * 层序遍历
     * @return
     */
    void levelOrder();

    /**
     * 插入节点
     * @param t
     */
    void insert(T t);

    /**
     * 移除节点
     * @param t
     */
    void remove(T t);

    /**
     * 查找节点
     * @param data
     * @return
     */
    Node find(T data);

    /**
     * 是否包含节点
     * @param data
     * @return
     */
    boolean contain(T data);

    /**
     * 查找最大值
     * @return
     */
    T findMax();

    /**
     * 查找最小值
     * @return
     */
    T findMin();

    /**
     * 树的深度
     * @return
     */
    int height();


}
