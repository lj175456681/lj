package netty.serialize;

import java.io.IOException;

/**
 * Title: DemoMain
 * Description:
 * author: liujie
 * date: 2017-08-17 下午4:19
 */
public class DemoMain {

    public static void main(String[] args) throws IOException {
        Student stu = new Student();
        stu.setAge(30);
        stu.setName("刘杰");
        stu.setSex(1);
        System.out.println();

        SerializeCompare hessian = new HessianSerialize();
        SerializeCompare java = new JavaSerialize();
        SerializeCompare kryo = new KryoSerialize();
        System.out.println("hessian 序列化字节长度 :" + hessian.serialize(stu));
        System.out.println("java 序列化字节长度 :" + java.serialize(stu));
        System.out.println("kryo 序列化字节长度 :" + kryo.serialize(stu));
        System.out.println("-------------------------------------------");
        final int times = 1000;
        System.out.println("hessian 执行耗时 ：" + hessian.costTime(stu,times));
        System.out.println("java 执行耗时 ："  + java.costTime(stu,times));
        System.out.println("kryo 执行耗时 ："  + kryo.costTime(stu,times));
    }
}
