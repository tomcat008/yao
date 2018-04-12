package com.itdreamworks.yao.controller;

import com.itdreamworks.yao.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
    @Autowired
    protected MenuMapper menuDao;
}
