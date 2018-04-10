package netty.client;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Title: ClientRequest
 * Description:
 * author: liujie
 * date: 2017-08-18 上午10:04
 */
public class ClientRequest implements Serializable {

    private static final long serialVersionUID = -8868030706852354761L;

    private String qyCustNo;

    private String qyTradeNo;

    private String mobile;

    public String getQyCustNo() {
        return qyCustNo;
    }

    public void setQyCustNo(String qyCustNo) {
        this.qyCustNo = qyCustNo;
    }

    public String getQyTradeNo() {
        return qyTradeNo;
    }

    public void setQyTradeNo(String qyTradeNo) {
        this.qyTradeNo = qyTradeNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
