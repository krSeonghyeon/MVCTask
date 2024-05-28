package com.example.demo.dto;



import java.util.List;

public class ResponsePostDTO {

    private String boardName;
    private List<ResponseArticleSimpleDTO> responseArticleSimpleDTOs;

    public ResponsePostDTO(String boardName, List<ResponseArticleSimpleDTO> responseArticleSimpleDTOs) {
        this.boardName = boardName;
        this.responseArticleSimpleDTOs = responseArticleSimpleDTOs;
    }

    public String getBoardName() {
        return boardName;
    }

    public List<ResponseArticleSimpleDTO> getResponseArticleSimpleDTOs() {
        return responseArticleSimpleDTOs;
    }
}
