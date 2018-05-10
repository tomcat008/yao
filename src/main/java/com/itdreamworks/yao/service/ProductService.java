package com.itdreamworks.yao.service;

import com.itdreamworks.yao.entity.Menu;
import com.itdreamworks.yao.entity.Product;
import com.itdreamworks.yao.mapper.MenuMapper;
import com.itdreamworks.yao.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productDao;

    @Transactional
    public void importProduct(List<Product> products){
        for(Product product : products){
            productDao.add(product);
        }
    }

    public List<com.itdreamworks.yao.model.Product> list(String startDate,String endDate){
        return productDao.list(startDate,endDate);
    }
}
