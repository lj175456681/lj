package tmp;

/**
 * Title: CustHello
 * Description:
 * author: liujie
 * date: 2017-07-25 下午4:56
 */
public class CustHello implements Hello {

    @Override
    public String sayHello() {
        System.out.println("CustHello");
        return " CustHello ";
    }
}
