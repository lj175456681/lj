package netty.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Title: KryoSerialize
 * Description:
 * author: liujie
 * date: 2017-08-17 下午4:32
 */
public class KryoSerialize implements SerializeCompare{

    @Override
    public int serialize(Object obj) throws IOException {
        Kryo kryo = new Kryo();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        kryo.writeObject(new Output(os), obj);
        return os.toByteArray().length;
    }

}
