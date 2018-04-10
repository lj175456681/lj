package netty.server;

import java.io.Serializable;

/**
 * Title: ServerResponse
 * Description:
 * author: liujie
 * date: 2017-08-18 上午10:09
 */
public class ServerResponse implements Serializable {

    private static final long serialVersionUID = 6069304508456413671L;

    private String dealNo;

    public String getDealNo() {
        return dealNo;
    }

    public void setDealNo(String dealNo) {
        this.dealNo = dealNo;
    }
}
