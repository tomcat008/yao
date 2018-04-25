package com.itdreamworks.yao.mapper;

import com.itdreamworks.yao.entity.Manager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ManagerMapper {
    List<Manager> findAll();
    Manager find(String loginId);
    int changePassword(Manager manager);
}
