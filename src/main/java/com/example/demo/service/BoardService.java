package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.dto.RequestBoardDTO;
import com.example.demo.exception.BoardNotFoundException;

import java.util.List;

public interface BoardService {

    public Board createBoard(RequestBoardDTO requestBoardDTO);
    public Board getBoardById(Long id) throws BoardNotFoundException;
    public List<Board> getBoards();
    public Board updateBoardById(Long id, RequestBoardDTO requestBoardDTO);
    public void deleteBoardById(Long id);
}
