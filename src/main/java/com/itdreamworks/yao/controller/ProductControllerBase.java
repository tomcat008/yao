package com.itdreamworks.yao.controller;

import com.itdreamworks.yao.entity.Article;
import com.itdreamworks.yao.entity.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/product")
public class ProductControllerBase extends BaseArticleController {

    /**
     * @param map
     * @return
     */
    @GetMapping(value = "")
    public String index(Map<String, Object> map, HttpServletRequest request) {
        prepareDataForCommandViewPart(map);
        Menu menu = getCurrentMenu(request);
        if (null != menu) {
            List<Article> articles = getMenuArticles(menu.getId());
            map.put("articles", articles);
        }
        return "/product/index";
    }
}
