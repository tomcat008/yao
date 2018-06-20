package com.itdreamworks.yao.controller;

import com.itdreamworks.yao.config.WebMvcConfig;
import com.itdreamworks.yao.config.WebPagesConfig;
import com.itdreamworks.yao.entity.Article;
import com.itdreamworks.yao.entity.Category;
import com.itdreamworks.yao.entity.Menu;
import com.itdreamworks.yao.entity.Product;
import com.itdreamworks.yao.mapper.ProductMapper;
import com.itdreamworks.yao.model.ProductResult;
import com.itdreamworks.yao.model.SimpleArticleForShow;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController extends BaseArticleController {

    @Autowired
    private ProductMapper productDao;
    @Autowired
    private WebPagesConfig webPagesConfig;

    private Map<String, String> getPageConfig(String path) {
        for (Map<String, String> page : webPagesConfig.getPagesMeta()) {
            if (page.get(WebPagesConfig.KEY_PATH).equals(path)) {
                return page;
            }
        }
        return null;
    }

    private String pageMethod(HttpServletRequest request, Map<String, Object> map, String path) {
        Map<String, String> page = getPageConfig(path);
        if (null != page) {
            map.put("page", page);
        }
        List<Menu> menus = prepareDataForViewPart(map);
        Menu menu = getCurrentMenu(request, menus);
        if (null != menu) {
            List<SimpleArticleForShow> articles = articleService.findByMenuIdForShow(menu.getId());
            map.put("articles", articles);
        }
        return "/home/index";
    }


    @RequestMapping("/")
    public String index(HttpServletRequest request, Map<String, Object> map) {
        return pageMethod(request, map, "index");
    }

    @RequestMapping("/article/{id}")
    public String article(@PathVariable("id") int id, Map<String, Object> map) {
        prepareDataForViewPart(map);
        Article article = articleService.find(id);
        map.put("article", article);
        return "/home/article";
    }

    @RequestMapping("/product")
    public String product(HttpServletRequest request, Map<String, Object> map) {
        return pageMethod(request, map, "product");
    }

    @RequestMapping("/qa")
    public String qa(HttpServletRequest request, Map<String, Object> map) {
        return pageMethod(request, map, "qa");
    }

    @RequestMapping("/about")
    public String about(HttpServletRequest request, Map<String, Object> map) {
        List<Menu> menus = prepareDataForViewPart(map);
        Menu menu = getCurrentMenu(request, menus);

        if (null != menu) {
            //List<SimpleArticleForShow> articles = articleService.findByMenuIdForShow(menu.getId());
            List<Article> articles = articleService.findByMenuIdForContent(menu.getId());
            if (articles.size() > 0) {
                map.put("article", articles.get(0));
                return "/home/about";
            }
        }
        map.put("title", menu.getTitle());
        return "/home/no";
    }

    @GetMapping("/validate")
    public String validate(HttpServletRequest request, Map<String, Object> map) {
        List<Menu> menus = prepareDataForViewPart(map);
        Menu menu = getCurrentMenu(request, menus);

        map.put("title", menu.getTitle());

        Map<String, String> page = getPageConfig("validate");
        if (null != page) {
            map.put("page", page);
        }


        return "/home/validate";
    }

    @PostMapping("/result")
    public String validateResult(String code, String vcode, HttpServletRequest request, Map<String, Object> map) {
        prepareDataForViewPart(map);
        Map<String, String> page = getPageConfig("validate");
        if (null != page) {
            map.put("page", page);
        }
        Object obj = request.getSession().getAttribute(ImageController.VCODE);
        ProductResult result = new ProductResult();

        if (vcode.equals(obj)) {
            Product product = productDao.find(code);
            if (null != product) {
                BeanUtils.copyProperties(product, result);
                if (!result.isUsed()) {
                    result.setCheckDate(new Date());
                    productDao.modify(code);
                }
            } else {
                result.setHave(false);
                result.setErrorMsg("<b>查无此物，考虑应为仿冒产品；购买请认准<code>南洋中医</code>品牌。</b>");
            }
        } else {
            result.setErrorMsg("<b>输入验证码错误，请重新输入。</b>");
        }
        map.put("result", result);
        return "/home/result";
    }


}
