package java8;

/**
 * Title: DefaultInterface
 * Description:
 * author: liujie
 * date: 2017-02-28 下午5:04
 */
@FunctionalInterface
public interface  DefaultInterface {

    default String sayName(){
        return "default name";
    }

    String getName();

}
