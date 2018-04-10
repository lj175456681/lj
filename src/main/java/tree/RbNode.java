package tree;

/**
 * Title: RbNode
 * Description: 红黑树节点
 * author: liujie
 * date: 2017-12-20 下午3:57
 */
public class RbNode<T extends Comparable> extends Node<T>{

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private T data;

    private RbNode left;

    private RbNode right;

    private RbNode parent;

    private boolean color;


    public RbNode(T data,RbNode parent){
        this.parent = parent;
        this.left = null;
        this.right = null;
        this.data = data;
        this.color = RED;
    }



    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public RbNode getLeft() {
        return left;
    }

    public void setLeft(RbNode left) {
        this.left = left;
    }

    @Override
    public RbNode getRight() {
        return right;
    }

    public void setRight(RbNode right) {
        this.right = right;
    }

    @Override
    public RbNode getParent() {
        return parent;
    }

    public void setParent(RbNode parent) {
        this.parent = parent;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
