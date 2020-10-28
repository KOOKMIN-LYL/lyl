package com.kookmin.lyl.web.dto;

public class JSONResult {
    private String result; // success, fail
    private String message; // if fail, set
    private Integer status;
    private Object data; // if success, set data

    public static JSONResult success(Object data) {
        return new JSONResult("success", null, data, 200);
    }

    public static JSONResult success(Object data, String value) {
        return new JSONResult("success", value, data, 200);
    }

    public static JSONResult fail(String message) {
        return new JSONResult("fail", message, null, 200);
    }

    private JSONResult(String result, String message, Object data, Integer status) {
        this.result = result;
        this.message = message;
        this.status = 200;
        this.data = data;
    }

    public JSONResult() {
        super();
        // TODO Auto-generated constructor stub
    }


    public void setResult(String result) {
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "JSONResult [result=" + result + ", message=" + message + ", data=" + data + "]";
    }
}
