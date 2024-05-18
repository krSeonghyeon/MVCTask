package com.example.demo.dto;

import java.time.LocalDateTime;

public class ResponseArticleDTO {

    private String title;
    private String author;
    private LocalDateTime date;
    private String content;

    public ResponseArticleDTO(String title, String author, LocalDateTime date, String content) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }
}
