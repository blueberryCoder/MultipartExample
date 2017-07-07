package com.blueberry.multipart.entity;

import java.util.List;

/**
 * Created by Administrator on 7/6/2017.
 */
public class UpLoadResponse {

    private int status;
    private List<PartInfo> partInfos;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<PartInfo> getPartInfos() {
        return partInfos;
    }

    public void setPartInfos(List<PartInfo> partInfos) {
        this.partInfos = partInfos;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
