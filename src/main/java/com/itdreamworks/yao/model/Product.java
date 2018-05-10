package com.itdreamworks.yao.model;

import com.itdreamworks.yao.config.WebMvcConfig;
import com.itdreamworks.yao.entity.Category;

import java.util.Date;

public class Product {
    private boolean used;

    public String getColorValue() {
        return colorValue;
    }

    public void setColorValue(String colorValue) {
        this.colorValue = colorValue;
    }

    private String colorValue;
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        this.categoryName = Category.getName(categoryId);
    }

    private int categoryId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code,pic,categoryName,description;
    private Date checkDate;


    public boolean isHave() {
        return true;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getPic() {
        return String.format("%s%d.jpg", WebMvcConfig.URL_CATEGORIES_DIRECTORY, categoryId);
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

}
