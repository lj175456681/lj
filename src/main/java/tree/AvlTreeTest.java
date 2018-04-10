package tree;

/**
 * Title: AvlTreeTest
 * Description:
 * author: liujie
 * date: 2017-12-19 下午4:26
 */
public class AvlTreeTest {

    public static void main(String[] args) {
        int[] a = new int[]{5,2,1,3,4,6};
        AvlTree avlTree = new AvlTree();
        for(int i : a){
            avlTree.insert(i);
        }
        avlTree.preOrder();

    }


}
