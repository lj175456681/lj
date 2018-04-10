package tree;

/**
 * Title: RbTree
 * Description: 红黑树
 * 红黑树的特性：
 * 1、每个节点或者是黑色，或者是红色
 * 2、根节点是黑色
 * 3、每个叶子节点是黑色。 [注意：这里叶子节点，是指为空的叶子节点！]
 * 4、如果一个节点是红色的，则它的子节点必须是黑色的。
 * 5、从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。
 * author: liujie
 * date: 2017-12-20 下午4:01
 */
public class RbTree<T extends Comparable> extends BinarySearchTree<T>{


    /**
     * 插入节点
     * 1、当前节点不为空，并且不是根节点，并且是红色
     *    ---> 如果父节点是左节点
     *        --> 获取父亲节点的兄弟节点（叔节点）
     *        --> 叔节点为红色
     *            --> 父节点、叔节点变为黑，爷爷节点变为红，爷爷节点变为当前节点
     *        --> 叔节点为黑色
     *            -->
     *
     * @param t
     */
    @Override
    public void insert(T t) {
        if(null == root){
            root = createNode(t,null);
        }
    }

    protected Node createNode(T t, Node parent) {
        size ++ ;
        return new RbNode(t,(RbNode)parent);
    }

    /**
     * 移除节点
     *
     * @param t
     */
    @Override
    public void remove(T t) {
        super.remove(t);
    }


}
