package com.example.demo.service;

import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.example.demo.dto.RequestArticleDTO;
import com.example.demo.dto.ResponseArticleDTO;
import com.example.demo.dto.ResponsePostDTO;
import com.example.demo.exception.ArticleNotFoundException;
import com.example.demo.exception.BoardNotFoundException;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    public ArticleServiceImpl(ArticleRepository articleRepository, BoardRepository boardRepository, MemberRepository memberRepository) {
        this.articleRepository = articleRepository;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public Article createArticle(RequestArticleDTO addArticleDTO) {
        return articleRepository.save(
                new Article(
                        addArticleDTO.getAuthorId(),
                        addArticleDTO.getBoardId(),
                        addArticleDTO.getTitle(),
                        addArticleDTO.getContent()
                )
        );
    }

    @Override
    public Article getArticleById(Long id) throws ArticleNotFoundException {
        return articleRepository.findById(id).orElseThrow(() ->
                new ArticleNotFoundException("Article not found with id: " + id));
    }

    @Override
    public List<ResponsePostDTO> getPosts() {
        List<Article> articles = articleRepository.findAll();
        List<Board> boards = boardRepository.findAll();
        Map<String, List<ResponseArticleDTO>> responsePostDTOsMap = new HashMap<>();

        for (Board board : boards) {
            responsePostDTOsMap.put(board.getName(), new ArrayList<>());
        }

        for (Article article : articles) {
            Board board = boardRepository.findById(article.getBoardId()).orElseThrow(() ->
                    new BoardNotFoundException("Board not found with id: " + article.getBoardId()));

            responsePostDTOsMap.get(board.getName()).add(convertToResponseArticleDTO(article));
        }

        List<ResponsePostDTO> responsePostDTOs = new ArrayList<>();

        for (Map.Entry<String, List<ResponseArticleDTO>> entry : responsePostDTOsMap.entrySet()) {
            responsePostDTOs.add(new ResponsePostDTO(entry.getKey(), entry.getValue()));
        }

        return responsePostDTOs;
    }

    @Override
    public List<ResponseArticleDTO> getArticles() {
        List<Article> articles = articleRepository.findAll();
        List<ResponseArticleDTO> responseArticleDTOs = new ArrayList<>();

        for (Article article : articles) {
            ResponseArticleDTO responseArticleDTO = convertToResponseArticleDTO(article);
            responseArticleDTOs.add(responseArticleDTO);
        }

        return responseArticleDTOs;
    }

    private ResponseArticleDTO convertToResponseArticleDTO(Article article) throws MemberNotFoundException {
        Member author = memberRepository.findById(article.getAuthorId()).orElseThrow(() ->
                new MemberNotFoundException("Member not found with id: " + article.getAuthorId()));
        String authorName = author.getName();

        return new ResponseArticleDTO(
                article.getTitle(),
                authorName,
                article.getCreated_at(),
                article.getContent()
        );
    }

    @Override
    public Article updateArticleById(Long id, RequestArticleDTO requestArticleDTO) {
        Article existArticle = articleRepository.findById(id).orElseThrow(() ->
                new ArticleNotFoundException("Article not found with id: " + id));

        Article updateArticle = new Article(
                requestArticleDTO.getAuthorId(),
                requestArticleDTO.getBoardId(),
                requestArticleDTO.getTitle(),
                requestArticleDTO.getContent()
        );

        updateArticle.setId(existArticle.getId());
        updateArticle.setCreated_at(existArticle.getCreated_at());
        updateArticle.setUpdated_at(LocalDateTime.now());

        return articleRepository.update(updateArticle);
    }

    @Override
    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }
}
