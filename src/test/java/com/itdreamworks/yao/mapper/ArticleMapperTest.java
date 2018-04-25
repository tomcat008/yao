package com.itdreamworks.yao.mapper;

import com.itdreamworks.yao.entity.Article;
import org.hibernate.validator.constraints.EAN;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleMapperTest {
    @Autowired
    ArticleMapper articleMapper;
    @Test
    public void findAll() {
        List<Article> ls = articleMapper.findAll();
        assertEquals(0,ls.size());
    }
}