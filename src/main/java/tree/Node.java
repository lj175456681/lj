package tree;

import java.io.Serializable;

/**
 * Title: Node
 * Description:
 * author: liujie
 * date: 2017-12-15 上午10:33
 */
public class Node<T extends Comparable> implements Serializable{

    private static final long serialVersionUID = 1308177533068528107L;

    private Node parent;

    private Node right;

    private Node left;

    protected T data;

    public Node(){

    }


    public Node(Node parent,Node right, Node left, T data) {
        this.parent = parent;
        this.right = right;
        this.left = left;
        this.data = data;
    }

    public Node(T data) {
        this.data = data;
        right = null;
        left = null;
        parent = null;
    }

    public Node(T data,Node parent){
        this.data = data;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }


    @Override
    public String toString() {
        return "data = [" + data.toString() +"]";
    }

    /**
     * 是否为叶子节点
     * @return
     */
    public boolean isLeaf(){
        return null == right && null == left;
    }


    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
