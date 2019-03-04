package com.gz.model;

import java.io.Serializable;
import java.util.Date;

public class ProductOrder implements Serializable {

    private int userId; //用户id

    private Date createTime; //创建时间

    private String tradeNo; //交易流水号

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
}
