package com.itdreamworks.yao.controller;

import com.itdreamworks.yao.config.ArticleDirectoryConfig;
import com.itdreamworks.yao.entity.Article;
import com.itdreamworks.yao.entity.Menu;
import com.itdreamworks.yao.model.SimpleArticleForManage;
import com.itdreamworks.yao.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/manage/art")
public class ArticleController extends BaseArticleController {
    private static final String BASE_PATH = "/manage/art";

    @Autowired
    private ArticleDirectoryConfig articleDirectoryConfig;

    @GetMapping(value = "/list")
    public String list() {
        return "redirect:" + BASE_PATH + "/list/0";
    }

    @PostMapping(value = "/list")
    public String list(@RequestParam(value = "menuId") int id) {
        return "redirect:" + BASE_PATH + "/list/" + id;
    }

    @RequestMapping(value = "/list/{menuId}")
    public String list(@PathVariable("menuId") int menuId, Map<String, Object> map) {
        List<Menu> menuList = menuService.findAll();
        map.put("id", menuId);
        map.put("menus", menuList);
        List<Article> list = null;
        if (0 == menuId)
            list = articleService.findAll();
        else
            list = articleService.findByMenuId(menuId);

        map.put("articles", list);
        return "/art/list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") int id) throws FileNotFoundException {
        if (articleService.delete(id)) {

//            File path = new File(ResourceUtils.getURL("classpath:").getPath());
//            File upload = new File(path.getAbsolutePath(), String.format("static/articles/%d/", id));
            File path = new File(articleDirectoryConfig.getDirectoryOrFilePath(""+id));
            if (path.exists())
                FileUtil.deleteDirectory(path);
        }

        return "redirect:" + BASE_PATH + "/list/0";

    }

    @RequestMapping(value = "/add")
    public String add(Map<String, Object> map) {
        List<Menu> menus = menuService.findAll();
        map.put("menus", menus);
        return "/art/add";
    }

    @PostMapping(value = "/addsave")
    public String addSave( SimpleArticleForManage article, Map<String, Object> map) throws FileNotFoundException {

        if (articleService.add(article)) {
//            File path = new File(ResourceUtils.getURL("classpath:").getPath());
//            File upload = new File(path.getAbsolutePath(), String.format("static/articles/%d/", article.getId()));
            File path = new File(articleDirectoryConfig.getDirectoryOrFilePath(""+article.getId()));
            if (!path.exists())
                path.mkdirs();

            return "redirect:" + BASE_PATH + "/list/0";
        } else {
            map.put("msg", "添加文章失败！");
            map.put("link", BASE_PATH + "/add");
            map.put("linkMsg", TRY_MSG);
            return "failed";
        }
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") int id, Map<String, Object> map) {
        Article article = articleService.find(id);
        map.put("article", article);
        List<Menu> menus = menuService.findAll();
        for (Menu m : menus) {
            m.setIsShow(false);
            int i = (article.getMenuId() & m.getId());
            if (i == m.getId())
                m.setIsShow(true);
        }
        map.put("menus", menus);

        return "/art/edit";
    }

    @PostMapping(value = "/editsave")
    public String editSave(SimpleArticleForManage article, Map<String, Object> map) {

        if (null != articleService.modify(article)) {
            return "redirect:" + BASE_PATH + "/list/0";
        } else {
            map.put("msg", "修改文章失败！");
            map.put("link", BASE_PATH + "/list");
            map.put("linkMsg", "返回文章列表");
            return "failed";
        }
    }

}
