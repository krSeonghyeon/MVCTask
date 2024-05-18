package com.example.demo.repository;

import com.example.demo.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository{

    private static final Map<Long, Article> articleStore = new HashMap<>();
    private static long articleSequence = 0L;

    @Override
    public Article save(Article article) {
        article.setId(++articleSequence);
        articleStore.put(article.getId(), article);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(articleStore.get(id));
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(articleStore.values());
    }

    @Override
    public Article update(Article article) {
        articleStore.put(article.getId(), article);
        return article;
    }

    @Override
    public void deleteById(Long id) {
        articleStore.remove(id);
    }
}
