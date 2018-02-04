package com.kingsley.androidnews.model.bean.wanandroid;

/**
 * class name : AuthData
 * created date : on 2017/12/20 18:14
 *
 * @author Kingsley
 * @version 1.0
 */

public class AuthData {

    /**
     * errorCode : 0
     * errorMsg : null
     * data : {"id":880,"username":"test~11111","password":"1234567890","icon":null,"type":0,"collectIds":[]}
     */
    private int errorCode;
    private String errorMsg;
    private WanAndroidUser data;

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

    public WanAndroidUser getData() {
        return data;
    }

    public void setData(WanAndroidUser data) {
        this.data = data;
    }


}