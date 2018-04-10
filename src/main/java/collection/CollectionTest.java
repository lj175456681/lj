package collection;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * Title: CollectionTest
 * Description:
 * author: liujie
 * date: 2017-07-28 下午6:11
 */
public class CollectionTest {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1,2,3);
        List<Integer> unmodifyList = Collections.unmodifiableList(list);
        System.out.println(unmodifyList);
//        unmodifyList.add(4);
        list.add(4);
        System.out.println(unmodifyList);
    }
}
