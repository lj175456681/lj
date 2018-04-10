package java8;

/**
 * Title: DefaultImpl
 * Description:
 * author: liujie
 * date: 2017-02-28 下午5:11
 */
public class DefaultImpl implements DefaultInterface {

    @Override
    public String sayName() {
        return "impl name";
    }

    @Override
    public String getName() {
        return null;
    }
}
