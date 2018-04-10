package tree;

/**
 * Title: AvlTree
 * Description: 旋转节点，参考地址 https://www.cnblogs.com/skywang12345/p/3577479.html
 * author: liujie
 * date: 2017-12-19 下午2:44
 */
public class AvlTree<T extends Comparable> extends BinarySearchTree<T> implements Tree<T>{

    /**
     * 插入节点
     *
     * @param t
     */
    @Override
    public void insert(T t) {
        super.insert(t);
        setRoot(rebalance(root));
    }

    /**
     * 移除节点
     *
     * @param t
     */
    @Override
    public void remove(T t) {
        super.remove(t);
        setRoot(rebalance(root));
    }

    /**
     * 重新平衡
     * @param node
     * @return
     */
    private Node rebalance(Node node) {
        int heightDifference = getHeightDifference(node);
        if(heightDifference > 1){
            int leftDifference = getHeightDifference(node.getLeft());
            if(leftDifference > 0){
                return ll(node);
            }else{
                return lr(node);
            }
        }else if(heightDifference < -1){
            int rightDifference = getHeightDifference(node.getRight());
            if(rightDifference < 0){
                return rr(node);
            }else{
                return rl(node);
            }
        }
        return node;
    }

    private int getHeightDifference(Node node) {
        if(null == node){
            return 0;
        }
        int heightLeft = 0;
        int heightRight = 0;
        if(node.getLeft() != null){
            heightLeft = height(node.getLeft());
        }
        if(node.getRight() != null){
            heightRight = height(node.getRight());
        }
        return heightLeft - heightRight;
    }


    private Node ll(Node node){
        Node left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        return left;
    }

    private Node rr(Node node){
        Node right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);
        return right;
    }

    private Node lr(Node node){
        node.setLeft(rr(node.getLeft()));
        return ll(node);
    }

    private Node rl(Node node){
        node.setRight(ll(node.getRight()));
        return rr(node);
    }

}
