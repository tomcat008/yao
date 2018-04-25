package com.itdreamworks.yao.controller;

import com.itdreamworks.yao.entity.Article;
import com.itdreamworks.yao.entity.Menu;
import com.itdreamworks.yao.model.SimpleArticleForShow;
import com.itdreamworks.yao.service.ArticleService;
import com.itdreamworks.yao.utils.LocalMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController  extends BaseArticleController{

    /**
     * 返回html模板.
     */
    @RequestMapping("/")
    public String index(HttpServletRequest request, Map<String, Object> map){
        prepareDataForCommandViewPart(map);
        Menu menu = getCurrentMenu(request);
        if(null != menu){
            List<SimpleArticleForShow> articles = articleService.findByMenuIdForShow(menu.getId());
            map.put("articles",articles);
        }
        return"/home/index";
    }

    @RequestMapping("/art/{id}")
    public String article(@PathVariable("id") int id, Map<String,Object> map){
        prepareDataForCommandViewPart(map);
        Article article = articleService.find(id);
        map.put("art",article);
        return"/home/art";
    }


//    @RequestMapping("/test")
//    public String test(Map<String,Object> map){
//        prepareDataForCommandViewPart(map);
//        Menu menu = menuService.find(1);
//        String welcome =  messageUtil.getMessage("welcome");
//        map.put("lang",messageUtil.getMessage("lang"));
//        map.put("title",menu.getTitle());
//        return"temp";
//    }


//    @RequestMapping("/changeLanauage")
//    public String changeSessionLanauage(HttpServletRequest request, HttpServletResponse response, String lang){
//
//        System.out.println(lang);
//
//        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
//
//        if("zh".equals(lang)){
//
//            localeResolver.setLocale(request, response, new Locale("zh", "CN"));
//
//        }else if("en".equals(lang)){
//
//            localeResolver.setLocale(request, response, new Locale("en", "US"));
//
//        }
//        return "redirect:/hello";
//    }
}
