package com.example.demo.controller;

import com.example.demo.dto.ResponsePostDTO;
import com.example.demo.dto.ResponsePostSimpleDTO;
import com.example.demo.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PageController {

    private final ArticleService articleService;

    public PageController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String getPosts(Model model) {
        List<ResponsePostSimpleDTO> responsePostSimpleDTOs = articleService.getPosts();
        model.addAttribute("posts", responsePostSimpleDTOs);
        return "posts";
    }

    @GetMapping(params = "boardId")
    public String getPostsByBoardId(@RequestParam Long boardId, Model model) {
        ResponsePostDTO responsePostDTO = articleService.getPostsByBoardId(boardId);
        model.addAttribute("boardName", responsePostDTO.getBoardName());
        model.addAttribute("articles", responsePostDTO.getResponseArticleSimpleDTOs());
        return "board-posts";
    }
}
