package com.wsh.springcloud.entity;

/**
 * @Description 支付实体类
 * @Date 2020/7/26 9:47
 * @Author weishihuai
 * 说明:
 */
public class Payment {
    private Long pkid;
    private String serial;

    public Payment(Long pkid, String serial) {
        this.pkid = pkid;
        this.serial = serial;
    }

    public Long getPkid() {
        return pkid;
    }

    public void setPkid(Long pkid) {
        this.pkid = pkid;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}