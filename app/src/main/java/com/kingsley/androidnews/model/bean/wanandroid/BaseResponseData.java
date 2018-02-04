package com.kingsley.androidnews.model.bean.wanandroid;

/**
 * class name : BaseResponseData
 * created date : on 2017/12/20 18:15
 *
 * @author Kingsley
 * @version 1.0
 */

public class BaseResponseData {

    /**
     * errorCode : 0
     * errorMsg : null
     * data : null
     */
    private int errorCode = -1;
    private String errorMsg;

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
}