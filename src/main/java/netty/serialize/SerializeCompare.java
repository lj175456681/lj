package netty.serialize;

import java.io.IOException;

/**
 * Title: SerializeCompare
 * Description:
 * author: liujie
 * date: 2017-08-17 下午4:22
 */
public interface SerializeCompare {

    int serialize(Object obj) throws IOException;

    default long costTime(Object obj,int times) throws IOException{
        long start = System.currentTimeMillis();
        for(int i = 0 ; i < times ; i++){
            this.serialize(obj);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
