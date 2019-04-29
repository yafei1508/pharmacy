package com.miaosha.response;

public class CommonReturnType {
    //表明对应请求的返回结果"success" 和 "fail"
    //TODO 申请失败时，status返回了refused,这些东西在哪儿设置比较好git pull origin master --allow-unrelated-histories
    private String status;
    private Object data;

    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result, "success");
    }
    public static CommonReturnType create(Object result, String status) {
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
