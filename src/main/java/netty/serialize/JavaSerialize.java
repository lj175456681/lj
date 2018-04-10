package netty.serialize;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Title: JavaSerialize
 * Description: java的序列化
 * author: liujie
 * date: 2017-08-17 下午4:11
 */
public class JavaSerialize implements  SerializeCompare{

    public int serialize(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.flush();
        oos.close();
        byte[] b = bos.toByteArray();
        bos.close();
        return b.length;
    }

}
