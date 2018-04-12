package com.itdreamworks.yao.controller;

import com.itdreamworks.yao.entity.Menu;
import com.itdreamworks.yao.utils.LocalMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController  extends BaseController{

    @Autowired
    private LocalMessageUtil messageUtil;
    /**
     * 返回html模板.
     */
    @RequestMapping({"/","/index","/home"})
    public String index(Map<String,Object> map){
        List<Menu> menuList = menuDao.findShow();
        String welcome =  messageUtil.getMessage("welcome");
        map.put("lang",messageUtil.getMessage("lang"));
        map.put("title",messageUtil.getMessage("title"));
        map.put("menus",menuList);
        return"/home";
    }

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
