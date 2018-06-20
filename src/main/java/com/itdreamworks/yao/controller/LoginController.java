package com.itdreamworks.yao.controller;

import com.itdreamworks.yao.entity.Manager;
import com.itdreamworks.yao.mapper.ManagerMapper;
import com.itdreamworks.yao.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value = "/system")
public class LoginController {
    private static final String BASE_PATH = "/system";
    public static final String LOGIN_URL = BASE_PATH;

    @Autowired
    private ManagerMapper managerDao;

    @GetMapping(value = {"", "/"})
    public String show() {
        return "/manage/login";
    }

    @PostMapping(value = "/login")
    public String login(String loginId, String password, Map<String, Object> map, HttpSession session) {
        Manager manager = managerDao.find(loginId);
        if (manager.getPassword().equals(Md5Util.encode(password))) {
            session.setAttribute(Manager.MANAGER_IDENTITY, loginId);
            return "redirect:" + MenuController.BASE_PATH + "/list";
        } else {
            map.put("msg", "用户名或密码错误！");
            map.put("link", BASE_PATH + "/login");
            map.put("linkMsg", "再次尝试登录");
            return "/failed";
        }
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(Manager.MANAGER_IDENTITY);
        return "redirect:" + LOGIN_URL;
    }
}
