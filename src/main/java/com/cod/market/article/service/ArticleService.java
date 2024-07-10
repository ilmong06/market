package com.cod.market.article.service;

import com.cod.market.article.entity.Article;
import com.cod.market.article.repository.ArticleRepository;
import com.cod.market.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void create(Member member,String title, String content) {
        Article article = new Article();
        article.setMember(member);
        article.setTitle(title);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        article.setModifyDate(LocalDateTime.now());

        articleRepository.save(article);
    }

    public Article findById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article ID: " + id));
    }
}