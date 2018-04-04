package com.welch.bean;

import java.math.BigDecimal;

/**
 * Created by pdclwc on 03/04/2018.
 */
public class Currency {
    private String code;
    private BigDecimal amount;
    private String info;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
