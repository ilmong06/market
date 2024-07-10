package com.cod.market.article.controller;

import com.cod.market.article.entity.Article;
import com.cod.market.article.service.ArticleService;
import com.cod.market.member.entity.Member;
import com.cod.market.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping("/article")
    @PreAuthorize("isAuthenticated()")
    public String articleForm() {
        return "article/article"; // 글 등록하는 페이지로 이동
    }

    @PostMapping("/article")
    @PreAuthorize("isAuthenticated()")
    public String createArticle(@RequestParam String title, @RequestParam String content, RedirectAttributes redirectAttributes) {
        Article article = articleService.articlecreate(title, content);
        // 생성된 게시물의 ID로 상세 페이지로 리다이렉트
        redirectAttributes.addAttribute("id", article.getId());
        return "redirect:/article/detail/{id}";
    }

    @GetMapping("/detail/{id}")
    public String detail(
            @PathVariable("id") Long id,
            Principal principal,
            @RequestParam("content") String content
    )  {
        // ArticleService를 통해 해당 ID에 해당하는 글을 가져옵니다.
        Member member = memberService.findByUserName(principal.getName());

        // 가져온 글을 모델에 추가하여 detail.html에서 사용할 수 있도록 합니다.
        articleService.create( member,title, content);

        return "article/detail"; // detail.html로 이동
    }
}