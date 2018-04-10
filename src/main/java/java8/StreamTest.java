package java8;

import bean.Person;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Title: StreamTest
 * Description:
 * author: liujie
 * date: 2017-03-03 上午11:05
 */
public class StreamTest {

    public static void main(String[] args) {
        List<String> list1 = Lists.newArrayList("a","b","c");
        List<String> list2 = Lists.newArrayList("b","c","d");
        List<String> list3 = Stream.concat(list1.stream(),list2.stream()).distinct().collect(Collectors.toList());
//        list3.forEach( s -> System.out.println(s));

        List<Integer> list4 = Lists.newArrayList(5,10);
        List<String> list5 = list4.stream().map(a -> a * a + "liujie").collect(Collectors.toList());
        List<String> list6 = Lists.transform(list4,a -> a * a + "liujie");
        list4.add(15);



        Person person1 = new Person();
        person1.setAge(18);
        person1.setName("Jay");
        person1.setSex(1);

        Person person2 = new Person();
        person2.setSex(0);
        person2.setName("Andy");
        person2.setAge(20);

        List<Person> list7 = Lists.newArrayList(person1,person2);

        list7.stream().filter(s->s.getAge() == 20).forEach(s -> System.out.println(s));
        

//        System.out.println(JSON.toJSONString(list6));

    }
}
