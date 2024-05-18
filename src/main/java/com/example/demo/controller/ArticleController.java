package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.dto.RequestArticleDTO;
import com.example.demo.dto.ResponseArticleDTO;
import com.example.demo.dto.ResponsePostDTO;
import com.example.demo.exception.ArticleNotFoundException;
import com.example.demo.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/articles")
    @ResponseBody
    public ResponseEntity<Article> createArticle(@RequestBody RequestArticleDTO requestArticleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.createArticle(requestArticleDTO));
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<ResponsePostDTO> responsePostDTOs = articleService.getPosts();
        model.addAttribute("posts", responsePostDTOs);
        return "posts";
    }

    @GetMapping("/articles")
    @ResponseBody
    public ResponseEntity<List<ResponseArticleDTO>> getArticles() {
        return ResponseEntity.ok(articleService.getArticles());
    }

    @GetMapping("/articles/{id}")
    @ResponseBody
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        try {
            Article article = articleService.getArticleById(id);
            return ResponseEntity.ok(article);
        } catch (ArticleNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/articles/{id}")
    @ResponseBody
    public ResponseEntity<Article> updateArticleById(@PathVariable Long id, @RequestBody RequestArticleDTO requestArticleDTO) {
        return ResponseEntity.ok().body(articleService.updateArticleById(id, requestArticleDTO));
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteArticleById(@PathVariable Long id) {
        articleService.deleteArticleById(id);
        return ResponseEntity.noContent().build();
    }
}
