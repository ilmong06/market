package com.cod.market.article.controller;

import com.cod.market.article.entity.Article;
import com.cod.market.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/article")
    @PreAuthorize("isAuthenticated()")
    public String article() {
        return "article/article";
    }

    @GetMapping("/detail/{id}")  // URL에 글의 ID를 포함시킵니다.
    public String detail(@PathVariable Long id, Model model) {
        // ArticleService를 통해 해당 ID에 해당하는 글을 가져옵니다.
        Article article = articleService.findById(id);

        // 가져온 글을 모델에 추가하여 detail.html에서 사용할 수 있도록 합니다.
        model.addAttribute("article", article);

        return "article/detail";
    }

    @PostMapping("/detail/{id}")
    public String createArticle(@RequestParam String title, @RequestParam String content) {
        articleService.articlecreate(title, content);
        // 저장 후 상세 페이지로 리다이렉트합니다.
        return "redirect:/article/detail/{id}";
    }
}