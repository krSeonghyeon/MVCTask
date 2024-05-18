package com.example.demo.service;

import com.example.demo.domain.Article;
import com.example.demo.dto.RequestArticleDTO;
import com.example.demo.dto.ResponseArticleDTO;
import com.example.demo.dto.ResponsePostDTO;
import com.example.demo.exception.ArticleNotFoundException;

import java.util.List;

public interface ArticleService {

    public Article createArticle(RequestArticleDTO requestArticleDTO);
    public Article getArticleById(Long id) throws ArticleNotFoundException;
    public List<ResponsePostDTO> getPosts();
    public List<ResponseArticleDTO> getArticles();
    public Article updateArticleById(Long id, RequestArticleDTO requestArticleDTO);
    public void deleteArticleById(Long id);
}
