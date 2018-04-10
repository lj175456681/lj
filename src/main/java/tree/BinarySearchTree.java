package tree;

/**
 * Title: BinarySearchTree
 * Description:
 * author: liujie
 * date: 2017-12-15 下午2:01
 */
public class BinarySearchTree<T extends Comparable> implements Tree<T>{

    /**
     * 根节点
     */
    protected Node root;

    /**
     * 节点个数
     */
    protected int size = 0;

    /**
     * 是否为空数
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return 0 == size;
    }

    /**
     * 节点个数
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 前序遍历 中->左->右
     *
     * @return
     */
    @Override
    public void preOrder() {
        if(null == root){
            System.out.println("空树");
        }
        pre(root);
        System.out.println();
    }

    private void pre(Node node) {
        if(null == node){
            return ;
        }
        System.out.print(node.getData() + " ");
        pre(node.getLeft());
        pre(node.getRight());
    }

    /**
     * 中序遍历
     *
     * @return
     */
    @Override
    public void midOrder() {
        if(null == root){
            System.out.println("空树");
        }
        mid(root);
        System.out.println();
    }

    private void mid(Node node) {
        if(null == node){
            return ;
        }
        mid(node.getLeft());
        System.out.print(node.getData() + " ");
        mid(node.getRight());
    }

    /**
     * 后续遍历
     *
     * @return
     */
    @Override
    public void postOrder() {
        if(null == root){
            System.out.println("空树");
        }
        post(root);
        System.out.println();
    }

    private void post(Node node) {
        if(null == node){
            return ;
        }
        post(node.getLeft());
        post(node.getRight());
        System.out.print(node.getData() + " ");
    }

    /**
     * 层序遍历
     *
     * @return
     */
    @Override
    public void levelOrder() {
    }

    /**
     * 插入节点
     *
     * @param t
     */
    @Override
    public void insert(T t) {
        if(null == t){
            throw new NullPointerException();
        }
        if(null == root){
            root = createNode(t,null);
        }else{
            insertNode(root,t);
        }
    }

    private void insertNode(Node node, T t) {
        if(node.getData().compareTo(t) < 0){
            Node right = node.getRight();
            if(null == right){
                right = createNode(t,node);
                node.setRight(right);
            }else{
                insertNode(right,t);
            }
        }else if(node.getData().compareTo(t) > 0){
            Node left = node.getLeft();
            if(null == left){
                left = createNode(t,node);
                node.setLeft(left);
            }else{
                insertNode(left,t);
            }
        }
    }

    protected Node createNode(T t,Node parent) {
        size ++ ;
        return new Node(t,parent);
    }


    /**
     * 移除节点
     *
     * @param t
     */
    @Override
    public void remove(T t) {
        Node node = find(t);
        if(null == node){
            return ;
        }
        if(node.isLeaf()){
            Node parent = node.getParent();
            if(null == parent){
                root = null;
            }
            if(node.getData().compareTo(parent.getData()) < 0){
                parent.setLeft(null);
            }else if(node.getData().compareTo(parent.getData()) > 0){
                parent.setRight(null);
            }
            node = null;
        }else if(node.getRight() != null &&  node.getLeft() != null){
            //右子树的最小值替换被删除的值
            Node rightMinNode = findMin(node.getRight());
            Node rightMindNodeParent = rightMinNode.getParent();
            if(rightMindNodeParent.getData().compareTo(rightMinNode.getData()) > 0){
                rightMindNodeParent.setLeft(null);
            }else if(rightMindNodeParent.getData().compareTo(rightMinNode.getData()) < 0){
                rightMindNodeParent.setRight(null);
            }
            node.setData(rightMinNode.getData());
            rightMinNode = null;
        }else{
            Node parent = node.getParent();
            Node child = node.getLeft() == null ? node.getRight() : node.getLeft();
            if(null == parent){
                root = child;
            }else{
                if(node.getData().compareTo(parent.getData()) < 0){
                    parent.setLeft(child);
                }else if(node.getData().compareTo(parent.getData()) > 0){
                    parent.setRight(child);
                }
            }
            node = null;
        }
    }

    /**
     * 查找节点
     *
     * @param data
     * @return
     */
    @Override
    public Node find(T data) {
        if(null == data){
            return null;
        }
        return findNode(root,data);
    }

    private Node findNode(Node node, T data) {
        if(null == node){
            return null;
        }else if(node.getData().compareTo(data) == 0){
            return node;
        }else if(node.getData().compareTo(data) > 0){
            return findNode(node.getLeft(),data);
        }else if(node.getData().compareTo(data) < 0){
            return findNode(node.getRight(),data);
        }
        return null;
    }

    /**
     * 是否包含节点
     *
     * @param data
     * @return
     */
    @Override
    public boolean contain(T data) {
        Node node = find(data);
        return null == node ? false : true;
    }

    /**
     * 查找最大值
     *
     * @return
     */
    @Override
    public T findMax() {
        Node node = findMax(root);
        if(null != node){
            return (T) node.getData();
        }
        return null;
    }

    private Node findMax(Node node){
        if(null == node){
            return null;
        }
        Node maxNode = node;
        while(true){
            Node right = maxNode.getRight();
            if(null == right){
                break;
            }
            maxNode = right;
        }
        return maxNode;
    }

    /**
     * 查找最小值
     *
     * @return
     */
    @Override
    public T findMin() {
        Node node = findMin(root);
        if(null == node){
            return null;
        }
        return (T) node.getData();
    }

    private Node findMin(Node node){
        if(null == node){
            return null;
        }
        Node minNode = node;
        while(true){
            Node left = minNode.getLeft();
            if(null == left){
                break;
            }
            minNode =  left;
        }
        return minNode;
    }

    /**
     * 树的深度
     *
     * @return
     */
    @Override
    public int height() {
        return height(root);
    }

    protected int height(Node node){
        if(null == node){
            return 0;
        }
        int leftHeight = height(node.getLeft()) + 1;
        int rightHeight = height(node.getRight()) + 1;
        return Math.max(leftHeight,rightHeight);
    }


    public Node getRoot() {
        return root;
    }

    protected void setRoot(Node root) {
        this.root = root;
    }
}
