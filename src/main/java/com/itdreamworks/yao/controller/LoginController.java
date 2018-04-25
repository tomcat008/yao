package com.itdreamworks.yao.controller;

import com.itdreamworks.yao.entity.Manager;
import com.itdreamworks.yao.mapper.ManagerMapper;
import com.itdreamworks.yao.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(value = "/system")
public class LoginController {
    private static final String BASE_PATH = "/system";

    @Autowired
    private ManagerMapper managerDao;

    @GetMapping(value = {"", "/"})
    public String show() {
        return "/manage/login";
    }

    @PostMapping(value = "/login")
    public String login(String loginId, String password, Map<String, Object> map) {
        Manager manager = managerDao.find(loginId);
        if (manager.getPassword().equals(Md5Util.encode(password))) {
            return "redirect:" + MenuController.BASE_PATH + "/list";
        } else {
            map.put("msg", "用户名或密码错误！");
            map.put("link", BASE_PATH + "/login");
            map.put("linkMsg", "再次尝试登录");
            return "failed";
        }
    }
}
