package com.itdreamworks.yao.mapper;

import com.itdreamworks.yao.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface ProductMapper {
    Product find(String code);
    List<com.itdreamworks.yao.model.Product> list(@Param("startDate") String startDate,@Param("endDate") String endDate);
    int add(Product product);
    int modify(String code);
}
