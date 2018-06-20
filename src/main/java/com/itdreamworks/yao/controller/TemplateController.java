package com.itdreamworks.yao.controller;

import com.itdreamworks.yao.utils.LocalMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

@Controller
public class TemplateController {

    @Autowired
    private LocalMessageUtil messageUtil;

    /**
     * 返回html模板.
     */
    @RequestMapping("/hello")
    public String helloHtml(Map<String, Object> map) {
        String welcome = messageUtil.getMessage("welcome");
        map.put("welcome", welcome);
        return "/helloHtml";
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
