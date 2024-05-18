package com.example.demo.dto;

import java.util.List;

public class ResponsePostDTO {

    private String boardName;
    private List<ResponseArticleDTO> responseArticleDTOs;

    public ResponsePostDTO(String boardName, List<ResponseArticleDTO> responseArticleDTOs) {
        this.boardName = boardName;
        this.responseArticleDTOs = responseArticleDTOs;
    }

    public List<ResponseArticleDTO> getResponseArticleDTOs() {
        return responseArticleDTOs;
    }

    public String getBoardName() {
        return boardName;
    }
}
