package proxy;

import tmp.CustHello;
import tmp.Hello;

import java.lang.reflect.Proxy;

/**
 * Title: ProxyMain
 * Description:
 * author: liujie
 * date: 2017-07-27 上午11:11
 */

public class ProxyMain {

    public static void main(String[] args) {
        Hello hello = new CustHello();
        Hello proxy = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(),
                new Class[]{Hello.class},
                new HelloProxy(hello));
        proxy.sayHello();

    }
}
