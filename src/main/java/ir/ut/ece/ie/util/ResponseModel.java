package ir.ut.ece.ie.util;

public class ResponseModel {
    private Boolean success;
    private Object data;

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
