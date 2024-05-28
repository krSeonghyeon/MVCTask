package com.example.demo.repository;

import com.example.demo.domain.Board;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcBoardRepository implements BoardRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcBoardRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Board save(Board board) {
        String sql = "insert into board (name) values (?)";
        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, board.getName());
            return ps;
        }, keyholder);

        Long id = keyholder.getKey().longValue();
        board.setId(id);
        return board;
    }

    @Override
    public Optional<Board> findById(Long id) {
        String sql = "select id, name from board where id = ?";

        try {
            Board board = jdbcTemplate.queryForObject(sql, boardRowMapper(), id);
            return Optional.of(board);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> new Board(
                rs.getLong("id"),
                rs.getString("name")
        );
    }

    @Override
    public List<Board> findAll() {
        String sql = "select id, name from board";
        return jdbcTemplate.query(sql, boardRowMapper());
    }

    @Override
    public Board update(Board board) {
        String sql = "update board set name = ? where id = ?";
        jdbcTemplate.update(sql, board.getName(), board.getId());
        return board;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from board where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
