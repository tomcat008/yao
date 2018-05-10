package com.itdreamworks.yao.service;

import com.itdreamworks.yao.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    ProductService service;

    @Test
    public void importProduct() {
        ArrayList<Product> products = new ArrayList<>(2);
        Product product = new Product();
        product.setCode("abc");
        product.setCategoryId(1);
        product.setCreateDate(new Date());
        product.setUsed(false);
        products.add(product);
        service.importProduct(products);
    }
}