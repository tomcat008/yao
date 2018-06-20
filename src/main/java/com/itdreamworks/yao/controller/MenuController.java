package com.itdreamworks.yao.controller;

import com.itdreamworks.yao.entity.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/manage/menu")
public class MenuController extends BaseController {
    public static final String BASE_PATH = "/manage/menu";

    @GetMapping(value = "/list")
    public String getAllMenus(Map<String, Object> map) {
        List<Menu> menus = menuService.findAll();
        map.put("menus", menus);
        return "/menu/list";
    }

    @GetMapping(value = "/edit/{id}")
    public String showEditForm(@PathVariable int id, Map<String, Object> map) {
        Menu menu = menuService.find(id);
        map.put("menu", menu);
        return "/menu/edit";
    }

    @GetMapping(value = "/add")
    public String showAddForm(Map<String, Object> map) {
        Menu menu = new Menu();
        map.put("menu", menu);
        return "/menu/add";
    }


    @PostMapping(value = "/addsave")
    public String addSave(Menu menu, Map<String, Object> map) {
        boolean flag = menuService.add(menu);

        if (flag) {
            return "redirect:" + BASE_PATH + "/list";
        } else {
            map.put("msg", "添加菜单失败！");
            map.put("link", BASE_PATH + "/add");
            map.put("linkMsg", TRY_MSG);
            return "/failed";
        }
    }

    @PostMapping(value = "/editsave")
    public String editSave(Menu menu, Map<String, Object> map) {
        boolean flag = false;
        menu = menuService.modify(menu);
        if (null != menu) {
            flag = true;
        }
        if (flag) {
            return "redirect:" + BASE_PATH + "/list";
        } else {
            map.put("msg", "编辑菜单失败！");
            map.put("link", BASE_PATH + "/list");
            map.put("linkMsg", "返回菜单列表");
            return "/failed";
        }
    }

}
