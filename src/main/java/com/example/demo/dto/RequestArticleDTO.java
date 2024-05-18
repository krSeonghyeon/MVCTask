package com.example.demo.dto;

public class RequestArticleDTO {

    private Long authorId;
    private Long boardId;
    private String title;
    private String content;

    public Long getAuthorId() {
        return authorId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

