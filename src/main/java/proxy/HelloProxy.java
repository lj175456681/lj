package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Title: HelloProxy
 * Description:
 * author: liujie
 * date: 2017-07-27 上午11:05
 */
public class HelloProxy implements InvocationHandler {

    private Object target;

    public HelloProxy(Object object){
        this.target = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("start");
        Object result = method.invoke(target,args);
        System.out.println("end");
        return result;
    }
}
