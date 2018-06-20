package com.itdreamworks.yao.controller;

import com.itdreamworks.yao.entity.Manager;
import com.itdreamworks.yao.mapper.ManagerMapper;
import com.itdreamworks.yao.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/manage/manager")
public class ManagerController {
    private static final String BASE_PATH = "/manage/manager";

    @Autowired
    private ManagerMapper managerDao;

    @GetMapping(value = "/list")
    public String list(Map<String, Object> map) {
        List<Manager> managers = managerDao.findAll();
        map.put("managers", managers);
        return "/manager/list";
    }

    @GetMapping(value = "/password/{loginId}")
    public String password(@PathVariable(value = "loginId") String loginId, Map<String, Object> map) {
        Manager manager = managerDao.find(loginId);
        map.put("manager", manager);
        return "/manager/password";
    }

    @PostMapping(value = "/changepassword")
    public String changePassword(String loginId, String oldPassword, String newPassword, Map<String, Object> map) {
        Manager manager = managerDao.find(loginId);
        oldPassword = Md5Util.encode(oldPassword);
        if (manager.getPassword().equals(oldPassword)) {
            manager.setPassword(Md5Util.encode(newPassword));
            managerDao.changePassword(manager);
            map.put("msg", "密码修改成功！");
            map.put("link", BASE_PATH + "/list");
            map.put("linkMsg", "返回用户管理");
            return "/success";
        } else {
            map.put("msg", "密码修改失败！");
            map.put("link", BASE_PATH + "/list");
            map.put("linkMsg", "返回用户管理");
            return "/failed";
        }
    }
}
