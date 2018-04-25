package com.itdreamworks.yao.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ContentArticleForManage {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String content;
    private String pic;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
