package com.example.demo.repository;

import com.example.demo.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    public Article save(Article article);
    public Optional<Article> findById(Long id);
    public List<Article> findAll();
    public Article update(Article article);
    public void deleteById(Long id);
}
