package com.example.demo.repository;

import com.example.demo.domain.Article;

import java.util.List;

public interface JdbcArticleRepository extends ArticleRepository {

    List<Article> findByBoardId(Long boardId);
}
