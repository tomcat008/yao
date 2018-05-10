package com.itdreamworks.yao.entity;

public enum Category {
    BiYanGao("鼻炎膏",1);
    private Category(String name,int value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private String name;
    private int value;

    public static String getName(int value) {
        for (Category c : Category.values()) {
            if (c.getValue() == value) {
                return c.name;
            }
        }
        return null;
    }

    public static int getValue(String name) {
        for (Category c : Category.values()) {
            if (c.getName().equals(name)) {
                return c.getValue();
            }
        }
        return -1;
    }
}
