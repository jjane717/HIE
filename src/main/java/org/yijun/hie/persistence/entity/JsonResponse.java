package org.yijun.hie.persistence.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuyijun on 14-11-12.
 */
public class JsonResponse {
    private Boolean success;

    private int total = 0;
    private List<AbstractJsonEntity> msg;

    public JsonResponse() {
        msg = new LinkedList<AbstractJsonEntity>();
    }

    public JsonResponse(Boolean success) {
        this.success = success;
        msg = new LinkedList<AbstractJsonEntity>();
    }

    public JsonResponse(Boolean success, List<AbstractJsonEntity> msg) {
        this.success = success;
        if (msg != null)
            this.msg = msg;
        else
            msg = new LinkedList<AbstractJsonEntity>();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public JsonResponse addResponseMsg(AbstractJsonEntity responseBody) {
        msg.add(responseBody);
        return this;
    }

    public List<AbstractJsonEntity> getMsg() {
        return msg;
    }

    public void setMsg(List<AbstractJsonEntity> msg) {
        this.msg = msg;
    }
}
