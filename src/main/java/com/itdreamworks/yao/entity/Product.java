package com.itdreamworks.yao.entity;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Product implements Serializable {
    private static final int COLUMN_CODE = 0;
    private static final int COLUMN_CATEGORY_ID = 1;
    private static final int COLUMN_CATEGORY_Name = 2;
    private static final int COLUMN_PIC = 3;
    private static final int COLUMN_COLOR_VALUE = 4;
    private static final int COLUMN_DESCRIPTION = 5;


    private int id;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    private int categoryId;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    private String categoryName;
    private String code, pic, colorValue, description;
    private boolean used;
    private Date createDate, checkDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getColorValue() {
        return colorValue;
    }

    public void setColorValue(String colorValue) {
        this.colorValue = colorValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void SetCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public static List<Product> getProductFromExcel(HSSFSheet sheet) {
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        List<Product> list = new ArrayList<>(physicalNumberOfRows - 1);
        Date date = new Date();
        for (int i = 0; i < physicalNumberOfRows; i++) {
            if (i == 0) {
                continue;//标题行
            }
            list.add(getProductFromExcelRow(sheet.getRow(i), date));
        }
        return list;
    }

    private static Product getProductFromExcelRow(HSSFRow row, Date createDate) {
        Product product = new Product();

        product.setCode(row.getCell(COLUMN_CODE).getStringCellValue());
        product.setCategoryId((int)row.getCell(COLUMN_CATEGORY_Name).getNumericCellValue());
        product.setCategoryName(row.getCell(COLUMN_CATEGORY_Name).getStringCellValue());
        product.setPic(null == row.getCell(COLUMN_PIC) ? null : row.getCell(COLUMN_PIC).getStringCellValue());
        product.setColorValue(null == row.getCell(COLUMN_COLOR_VALUE) ? null : row.getCell(COLUMN_COLOR_VALUE).getStringCellValue());
        product.setDescription(null == row.getCell(COLUMN_DESCRIPTION) ? null : row.getCell(COLUMN_DESCRIPTION).getStringCellValue());
        product.setCreateDate(createDate);
        product.setUsed(false);
        return product;
    }
}
