package com.data.elasticsearch;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping("/search")
    public List<Article> searchArticle(@RequestBody SearchDTO params) {
        return articleService.searchArticle(params.getKeyword(), params.getPage(), params.getPageSize());
    }
}
