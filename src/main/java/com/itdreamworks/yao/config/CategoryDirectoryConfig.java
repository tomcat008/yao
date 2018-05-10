package com.itdreamworks.yao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CategoryDirectoryConfig {

    public String getCategoryDirectoryPath() {
        return categoryDirectoryPath.endsWith("/") ? categoryDirectoryPath : categoryDirectoryPath + "/";
    }

    @Value("${web.category.directory}")
    private String categoryDirectoryPath;


}
