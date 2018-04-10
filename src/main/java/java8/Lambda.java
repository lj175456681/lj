package java8;

import bean.Person;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Title: Lambda
 * Description:
 * author: liujie
 * date: 2017-03-01 下午6:11
 */
public class Lambda {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1,2,3,4,5);

        System.out.println(list.stream().filter( s -> s ==4 ).collect(Collectors.toList()));

//        list.forEach((s)-> System.out.println(s ));

        List<Integer> nums = Lists.newArrayList(1,null,3,4,null,6);

//        System.out.println(nums.stream().filter(num -> num!= null).count());

        Person person1 = new Person();
        person1.setAge(18);
        person1.setName("Jay");
        person1.setSex(1);

        Person person2 = new Person();
        person2.setSex(0);
        person2.setName("Andy");
        person2.setAge(20);

        List<Person> persons = Lists.newArrayList(person1,person2);


        List<String> an = persons.stream().map(s -> s.getName()).collect(Collectors.toList());
        System.out.println(an);
//        an.forEach(a -> System.out.println(a));




    }





}
