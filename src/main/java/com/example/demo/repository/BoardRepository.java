package com.example.demo.repository;

import com.example.demo.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    public Board save(Board board);
    public Optional<Board> findById(Long id);
    public List<Board> findAll();
    public Board update(Board board);
    public void deleteById(Long id);
}
