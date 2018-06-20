package com.itdreamworks.yao.mapper;

import com.itdreamworks.yao.entity.Menu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuMapper {
    List<Menu> findAll();

    List<Menu> findShow();

    Menu find(int id);

    int modify(Menu menu);

    int add(Menu menu);
}
