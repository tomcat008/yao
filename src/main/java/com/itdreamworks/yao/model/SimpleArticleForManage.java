package com.itdreamworks.yao.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleArticleForManage {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String menuId;
    private String title;
    private String author;
    private String publishDate;

    public boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(boolean show) {
        isShow = show;
    }

    private boolean isShow;

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String keyWords;
    private String description;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public int getMenuId() {
        if (null == menuId)
            return 0;
        String[] ids = menuId.split(",");
        int i = 0;
        for (String id : ids)
            i |= Integer.parseInt(id);
        return i;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishDate() {
        try {
            return format.parse(publishDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
