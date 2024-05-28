package com.example.demo.service;

import com.example.demo.domain.Article;
import com.example.demo.dto.*;
import com.example.demo.exception.ArticleNotFoundException;

import java.util.List;

public interface ArticleService {

    public Article createArticle(RequestArticleDTO requestArticleDTO);
    public Article getArticleById(Long id) throws ArticleNotFoundException;
    public List<ResponsePostSimpleDTO> getPosts();
    public ResponsePostDTO getPostsByBoardId(Long boardId);
    public List<ResponseArticleDTO> getArticles();
    public List<Article> getArticleByBoardId(Long boardId);
    public Article updateArticleById(Long id, ModifyArticleDTO modifyArticleDTO);
    public void deleteArticleById(Long id);
}
