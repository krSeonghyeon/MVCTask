package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.dto.RequestBoardDTO;
import com.example.demo.exception.BoardNotFoundException;
import com.example.demo.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    @Transactional
    public Board createBoard(RequestBoardDTO requestBoardDTO) {
        return boardRepository.save(new Board(requestBoardDTO.getName()));
    }

    @Override
    public Board getBoardById(Long id) throws BoardNotFoundException {
        return boardRepository.findById(id).orElseThrow(() ->
                new BoardNotFoundException("Board not found with id: " + id));
    }

    @Override
    public List<Board> getBoards(){
        return boardRepository.findAll();
    }

    @Override
    @Transactional
    public Board updateBoardById(Long id, RequestBoardDTO requestBoardDTO) {
        Board existBoard = boardRepository.findById(id).orElseThrow(() ->
                new BoardNotFoundException("Board not found with id: " + id));

        Board updateBoard = new Board(requestBoardDTO.getName());
        updateBoard.setId(existBoard.getId());

        return boardRepository.update(updateBoard);
    }

    @Override
    @Transactional
    public void deleteBoardById(Long id) {
        boardRepository.deleteById(id);
    }
}
