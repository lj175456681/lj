package netty.serialize;

import com.alibaba.com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Title: HessianSerialize
 * Description:
 * author: liujie
 * date: 2017-08-17 下午4:18
 */
public class HessianSerialize implements SerializeCompare {

    public int serialize(Object obj) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(obj);
        return os.toByteArray().length;
    }

}
