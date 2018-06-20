package com.itdreamworks.yao.controller;

import com.itdreamworks.yao.entity.Article;
import com.itdreamworks.yao.entity.Menu;
import com.itdreamworks.yao.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseArticleController extends BaseController {
    @Autowired
    protected ArticleService articleService;


    protected List<Article> getMenuArticles(int menuId) {
        return articleService.findByMenuId(menuId);
    }


}
