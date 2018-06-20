package com.itdreamworks.yao.entity;

import java.io.Serializable;

public class Menu implements Serializable {
    private int id, sortRight;
    private boolean isShow;

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    private boolean current;
    private String title, url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSortRight() {
        return sortRight;
    }

    public void setSortRight(int sortRight) {
        this.sortRight = sortRight;
    }

    public boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
