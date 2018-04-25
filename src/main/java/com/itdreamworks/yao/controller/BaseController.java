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
    MenuService menuService;

    protected Menu getCurrentMenu(HttpServletRequest request){
        String path = request.getRequestURI();
        List<Menu> menuList = menuService.findShow();
        Menu menu = null;
        for (Menu m : menuList)
        {
            if(m.getUrl().equals(path) || m.getUrl().equals(path.substring(0,path.length()-1)))
            {
                menu =m;
                break;
          }
        }
        return menu;
    }

    protected void prepareDataForCommandViewPart(Map<String,Object> map){
        List<Menu> menuList = menuService.findAll();
        map.put("menus",menuList);
    }

}
