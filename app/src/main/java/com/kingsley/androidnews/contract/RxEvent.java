package com.kingsley.androidnews.contract;

/**
 * class name : RxEvent
 * created date : on 2018/1/28 17:31
 *
 * @author Kingsley
 * @version 1.0
 */

public class RxEvent {
    private String type;

    public RxEvent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RxEvent{" +
                "type='" + type + '\'' +
                '}';
    }
}
