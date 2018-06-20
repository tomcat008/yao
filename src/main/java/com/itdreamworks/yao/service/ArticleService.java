package com.itdreamworks.yao.service;

import com.itdreamworks.yao.entity.Article;
import com.itdreamworks.yao.mapper.ArticleMapper;
import com.itdreamworks.yao.model.ContentArticleForManage;
import com.itdreamworks.yao.model.SimpleArticleForManage;
import com.itdreamworks.yao.model.SimpleArticleForShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "art")
@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleDao;

    public List<Article> findAll() {
        return articleDao.findAll();
    }

    public List<Article> findByMenuId(int id) {
        return articleDao.findByMenuId(id);
    }

    public List<Article> findByMenuIdForContent(int id) {
        return articleDao.findByMenuIdForContent(id);
    }

    public List<SimpleArticleForShow> findByMenuIdForShow(int id) {
        return articleDao.findByMenuIdForShow(id);
    }

    public Article find(int id) {
        return articleDao.find(id);
    }

    public SimpleArticleForManage modify(SimpleArticleForManage article) {
        return articleDao.modify(article) == 1 ? article : null;
    }

    public boolean modifyContent(ContentArticleForManage article) {
        return articleDao.modifyContent(article) == 1;
    }

    public boolean add(SimpleArticleForManage article) {
        articleDao.add(article);
        return article.getId() > 0;
    }

    public boolean delete(int id) {
        return articleDao.delete(id) == 1;
    }
}