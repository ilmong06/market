package com.cod.market.article.controller;

import com.cod.market.article.entity.Article;
import com.cod.market.article.form.ArticleForm;
import com.cod.market.article.service.ArticleService;
import com.cod.market.member.entity.Member;
import com.cod.market.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String createArticleForm(Model model) {
        model.addAttribute("articleForm", new ArticleForm());
        return "article/create"; // 글 등록하는 페이지로 이동
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String createArticle(@Valid @ModelAttribute("articleForm") ArticleForm articleForm,
                                BindingResult bindingResult,
                                Principal principal,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "article/create";
        }

        Member member = memberService.findByUserName(principal.getName());
        Article article = articleService.create(member, articleForm.getTitle(), articleForm.getContent());

        redirectAttributes.addAttribute("id", article.getId());
        return "redirect:/article/detail/{id}";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Article article = articleService.findById(id);
        if (article == null) {
            // Handle case where article is not found
            return "redirect:/article/list"; // Redirect to list page or handle as needed
        }
        model.addAttribute("article", article);
        return "article/detail"; // detail.html로 이동
    }


}
