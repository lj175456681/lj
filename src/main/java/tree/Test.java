package tree;

/**
 * Title: Test
 * Description:
 * author: liujie
 * date: 2017-12-15 上午11:22
 */
public class Test {

    public static void main(String[] args) {
        int a[] =  new int[]{36,79,16,68,56,42,69,74,80,31};
        BinarySearchTree<Integer> tree = new BinarySearchTree();
        for(int i = 0 ; i< 10 ; i++){
            tree.insert(a[i]);
        }

        System.out.println(tree.height());

        /**
        tree.preOrder();
        tree.midOrder();
        tree.postOrder();
        System.out.println("isEmpty:" + tree.isEmpty());
        System.out.println(tree.contain(80));
        System.out.println("树节点数：" + tree.size());
        System.out.println(tree.find(31));
        System.out.println(tree.findMax());
        System.out.println(tree.findMin());
         **/

    }

}
