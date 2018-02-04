package com.kingsley.androidnews.model.bean.wanandroid;

import java.util.List;

/**
 * class name : WanAndroidUser
 * created date : on 2017/12/20 20:09
 *  WanAndroid用户信息
 * @author Kingsley
 * @version 1.0
 */

public class WanAndroidUser {

    /**
     * id : 880
     * username : test~11111
     * password : 123****890
     * icon : null
     * type : 0
     * collectIds : []
     */
    private int id;
    private String username;
    private String password;
    private String icon;
    private int type;
    private List<Integer> collectIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }
}
