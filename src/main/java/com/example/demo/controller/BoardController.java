package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.dto.RequestBoardDTO;
import com.example.demo.exception.BoardNotFoundException;
import com.example.demo.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody RequestBoardDTO requestBoardDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.createBoard(requestBoardDTO));
    }

    @GetMapping
    public ResponseEntity<List<Board>> getBoards() {
        return ResponseEntity.ok(boardService.getBoards());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        try {
            Board board = boardService.getBoardById(id);
            return ResponseEntity.ok(board);
        } catch (BoardNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoardById(@PathVariable Long id, @RequestBody RequestBoardDTO requestBoardDTO) {
        return ResponseEntity.ok().body(boardService.updateBoardById(id, requestBoardDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoardById(@PathVariable Long id) {
        boardService.deleteBoardById(id);
        return ResponseEntity.noContent().build();
    }
}
