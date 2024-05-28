package com.example.demo.repository;

import com.example.demo.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

    private static final Map<Long, Board> boardStore = new HashMap<>();
    private static long boardSequence = 0L;

    @Override
    public Board save(Board board) {
        board.setId(++boardSequence);
        boardStore.put(board.getId(), board);
        return board;
    }

    @Override
    public Optional<Board> findById(Long id) {
        return Optional.ofNullable(boardStore.get(id));
    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(boardStore.values());
    }

    @Override
    public Board update(Board board) {
        boardStore.put(board.getId(), board);
        return board;
    }

    @Override
    public void deleteById(Long id) {
        boardStore.remove(id);
    }
}