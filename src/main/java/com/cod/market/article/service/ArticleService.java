package com.cod.market.article.service;

import com.cod.market.article.entity.Article;
import com.cod.market.article.repository.ArticleRepository;
import com.cod.market.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article create(Member member, String title, String content) {
        Article article = new Article();
        article.setMember(member);
        article.setTitle(title);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        article.setModifyDate(LocalDateTime.now());

        return articleRepository.save(article);
    }

    public Article findById(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        return optionalArticle.orElse(null);
    }

    public Article save(Article article) {
        article.setModifyDate(LocalDateTime.now());
        return articleRepository.save(article);
    }

    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }
}