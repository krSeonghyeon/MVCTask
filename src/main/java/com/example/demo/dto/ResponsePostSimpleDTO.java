package com.example.demo.dto;

import java.util.List;

public class ResponsePostSimpleDTO {

    private String boardName;
    private List<ResponseArticleDTO> responseArticleDTOs;

    public ResponsePostSimpleDTO(String boardName, List<ResponseArticleDTO> responseArticleDTOs) {
        this.boardName = boardName;
        this.responseArticleDTOs = responseArticleDTOs;
    }

    public String getBoardName() {
        return boardName;
    }

    public List<ResponseArticleDTO> getResponseArticleDTOs() {
        return responseArticleDTOs;
    }
}
