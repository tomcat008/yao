package com.itdreamworks.yao.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "web")
public class WebPagesConfig {
    public static final String KEY_PATH="path";
    public static final String KEY_TITLE="title";
    public static final String KEY_KEYWORDS="keywords";
    public static final String KEY_DESCRIPTION="description";

    public List<Map<String, String>> getPagesMeta() {
        return pagesMeta;
    }

    public void setPagesMeta(List<Map<String, String>> pagesMeta) {
        this.pagesMeta = pagesMeta;
    }

    private List<Map<String, String>> pagesMeta = new ArrayList<>();

}
