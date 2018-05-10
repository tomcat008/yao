package com.itdreamworks.yao.model;

public class ProductResult extends Product {
    private String errorMsg;
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
