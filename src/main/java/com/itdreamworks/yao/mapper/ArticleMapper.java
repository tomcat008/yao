package com.itdreamworks.yao.mapper;

import com.itdreamworks.yao.entity.Article;
import com.itdreamworks.yao.entity.Menu;
import com.itdreamworks.yao.model.ContentArticleForManage;
import com.itdreamworks.yao.model.SimpleArticleForManage;
import com.itdreamworks.yao.model.SimpleArticleForShow;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ArticleMapper {
    List<Article> findAll();
    List<Article> findByMenuId(int menuId);
    List<SimpleArticleForShow> findByMenuIdForShow(int menuId);
    Article find(int id);
    int modify(SimpleArticleForManage article);
    int modifyContent(ContentArticleForManage article);
    int add(SimpleArticleForManage article);
    int delete(int id);
}
