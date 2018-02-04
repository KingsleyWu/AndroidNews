package com.kingsley.androidnews.model.bean.wanandroid;

import java.util.List;

/**
 * class name : BannerBean
 * created date : on 2018/1/20 16:19
 *
 * @author Kingsley
 * @version 1.0
 */

public class BannerData {

    /**
     * errorCode : 0
     * errorMsg : null
     * data : [{"id":6,"url":"http://www.wanandroid.com/navi","imagePath":"http://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png","title":"我们新增了一个常用导航Tab~","desc":"","isVisible":1,"order":1,"type":0},{"id":7,"url":"http://www.wanandroid.com/blog/show/10","imagePath":"http://www.wanandroid.com/blogimgs/ffb61454-e0d2-46e7-bc9b-4f359061ae20.png","title":"送你一个暖心的Mock API工具","desc":"","isVisible":1,"order":2,"type":0},{"id":3,"url":"http://www.wanandroid.com/article/list/0?cid=254","imagePath":"http://www.wanandroid.com/blogimgs/fb0ea461-e00a-482b-814f-4faca5761427.png","title":"兄弟，要不要挑个项目学习下?","desc":"","isVisible":1,"order":3,"type":0},{"id":4,"url":"http://www.wanandroid.com/article/list/0?cid=73","imagePath":"http://www.wanandroid.com/blogimgs/ab17e8f9-6b79-450b-8079-0f2287eb6f0f.png","title":"看看别人的面经，搞定面试~","desc":"","isVisible":1,"order":3,"type":0},{"id":2,"url":"http://www.wanandroid.com/tools/bejson","imagePath":"http://www.wanandroid.com/blogimgs/90cf8c40-9489-4f9d-8936-02c9ebae31f0.png","title":"JSON工具","desc":"","isVisible":1,"order":2,"type":1},{"id":5,"url":"http://www.wanandroid.com/blog/show/6","imagePath":"http://www.wanandroid.com/blogimgs/acc23063-1884-4925-bdf8-0b0364a7243e.png","title":"微信文章合集","desc":"","isVisible":1,"order":3,"type":1}]
     */

    private int errorCode;
    private String errorMsg;
    private List<Data> data;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        /**
         * id : 6
         * url : http://www.wanandroid.com/navi
         * imagePath : http://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png
         * title : 我们新增了一个常用导航Tab~
         * desc : 
         * isVisible : 1
         * order : 1
         * type : 0
         */

        private int id;
        private String url;
        private String imagePath;
        private String title;
        private String desc;
        private int isVisible;
        private int order;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getIsVisible() {
            return isVisible;
        }

        public void setIsVisible(int isVisible) {
            this.isVisible = isVisible;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
