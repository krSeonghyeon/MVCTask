package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.dto.ModifyArticleDTO;
import com.example.demo.dto.RequestArticleDTO;
import com.example.demo.dto.ResponseArticleDTO;
import com.example.demo.exception.ArticleNotFoundException;
import com.example.demo.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody RequestArticleDTO requestArticleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.createArticle(requestArticleDTO));
    }

    @GetMapping
    public ResponseEntity<List<ResponseArticleDTO>> getArticles() {
        return ResponseEntity.ok(articleService.getArticles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        try {
            Article article = articleService.getArticleById(id);
            return ResponseEntity.ok(article);
        } catch (ArticleNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(params = "boardId")
    public ResponseEntity<List<Article>> getArticleByBoardId(@RequestParam Long boardId) {
        return ResponseEntity.ok(articleService.getArticleByBoardId(boardId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticleById(@PathVariable Long id, @RequestBody ModifyArticleDTO modifyArticleDTO) {
        return ResponseEntity.ok().body(articleService.updateArticleById(id, modifyArticleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticleById(@PathVariable Long id) {
        articleService.deleteArticleById(id);
        return ResponseEntity.noContent().build();
    }
}
