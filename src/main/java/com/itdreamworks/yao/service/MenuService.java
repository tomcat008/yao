package com.itdreamworks.yao.service;

import com.itdreamworks.yao.entity.Menu;
import com.itdreamworks.yao.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.List;

@CacheConfig(cacheNames = "menu")
@Service
public class MenuService {
    @Autowired
    private MenuMapper menuDao;

    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    @Cacheable(key = "\"SHOW\"")
    public List<Menu> findShow() {
        return menuDao.findShow();
    }

    public Menu find(int id) {
        return menuDao.find(id);
    }


    @CacheEvict(key = "\"SHOW\"")
    public Menu modify(Menu menu) {
        return menuDao.modify(menu) == 1?menu:null;
    }

    @CacheEvict(key = "\"SHOW\"")
    public boolean add(Menu menu) {
        return menuDao.add(menu) == 1;
    }
}
