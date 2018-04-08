package com.itdreamworks.yao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;
import java.util.Map;

@Controller
public class TemplateController {

    @Autowired
    private MessageSource messageSource;
    /**

     * 返回html模板.

     */

    @RequestMapping("/hello")
    public String helloHtml(Map<String,Object> map){
        Locale locale = LocaleContextHolder.getLocale();
        String welcome = messageSource.getMessage("welcome",null,locale);
        map.put("welcome",welcome);
        return"/helloHtml";
    }
}
