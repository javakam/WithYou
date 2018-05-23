package com.sq.data.wandroid;

/**
 * WanAndroid网络请求基本数据结构
 * <p>
 *
 * @author javakam
 * @date 2018/5/23
 */
public class WanResponse<T> {
    /**
     * 0：成功，1：失败
     */
    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
