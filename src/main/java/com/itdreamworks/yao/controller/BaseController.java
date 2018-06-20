package com.itdreamworks.yao.controller;

import com.itdreamworks.yao.entity.Menu;
import com.itdreamworks.yao.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class BaseController {
    public static final String TRY_MSG = "点我再试一下...";
    @Autowired
    protected MenuService menuService;

    protected Menu getCurrentMenu(HttpServletRequest request, List<Menu> menus) {
        String path = request.getRequestURI();
        Menu menu = null;
        for (Menu m : menus) {
            m.setCurrent(false);
            if (m.getUrl().equals(path) || m.getUrl().equals(path.substring(0, path.length() - 1))) {
                menu = m;
                menu.setCurrent(true);
            }
        }
        return menu;
    }

    protected List<Menu> prepareDataForViewPart(Map<String, Object> map) {
        List<Menu> menuList = menuService.findShow();
        map.put("menus", menuList);
        return menuList;
    }

}
