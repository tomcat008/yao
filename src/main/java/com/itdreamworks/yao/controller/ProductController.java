package com.itdreamworks.yao.controller;

import com.itdreamworks.yao.entity.Product;
import com.itdreamworks.yao.service.ProductService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/manage/product")
public class ProductController {
    private static final String BASE_PATH = "/manage/product";


    @Autowired
    private ProductService productService;

    @GetMapping(value = "/list")
    public String list() {
        return "/product/list";
    }

    @PostMapping(value = "/list")
    public String list(@RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate, Map<String, Object> map) {
        map.put("products", productService.list(startDate, endDate));
        return "/product/list";
    }

    @GetMapping(value = "/import")
    public String importProduct() {
        return "/product/import";
    }

    @PostMapping(value = "/import")
    public String importProduct(@RequestParam("data") MultipartFile data, Map<String, Object> map) throws IOException {
        if (null != data) {
            HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(data.getInputStream()));
            HSSFSheet sheet = workbook.getSheetAt(0);
            List<Product> products = Product.getProductFromExcel(sheet);
            if (products.size() > 0) {
                productService.importProduct(products);
                map.put("msg", "产品数据导入成功！");
                map.put("link", BASE_PATH + "/list");
                map.put("linkMsg", "查看产品信息");
                return "success";
            }
        }
        map.put("msg", "无效的产品导入操作！");
        map.put("link", BASE_PATH + "/import");
        map.put("linkMsg", "再试一次");
        return "failed";
    }

}
