package com.example.demo.repository;

import com.example.demo.domain.Article;
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
public class JdbcArticleRepositoryImpl implements JdbcArticleRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcArticleRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Article save(Article article) {
        String sql = "insert into article (author_id, board_id, title, content) values (?, ?, ?, ?)";
        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, article.getAuthorId());
            ps.setLong(2, article.getBoardId());
            ps.setString(3, article.getTitle());
            ps.setString(4, article.getContent());
            return ps;
        }, keyholder);

        Long id = keyholder.getKey().longValue();
        return findById(id).orElse(null);
    }

    @Override
    public Optional<Article> findById(Long id) {
        String sql = "select id, author_id, board_id, title, content, created_date, modified_date from article where id = ?";

        try {
            Article article = jdbcTemplate.queryForObject(sql, articleRowMapper(), id);
            return Optional.of(article);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> new Article(
                rs.getLong("id"),
                rs.getLong("author_id"),
                rs.getLong("board_id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getTimestamp("created_date").toLocalDateTime(),
                rs.getTimestamp("modified_date").toLocalDateTime()
        );
    }

    @Override
    public List<Article> findAll() {
        String sql = "select id, author_id, board_id, title, content, created_date, modified_date from article";
        return jdbcTemplate.query(sql, articleRowMapper());
    }

    @Override
    public List<Article> findByBoardId(Long boardId) {
        String sql = "select id, author_id, board_id, title, content, created_date, modified_date from article where board_id = ?";
        return jdbcTemplate.query(sql, articleRowMapper(), boardId);
    }

    @Override
    public Article update(Article article) {
        String sql = "update article set author_id = ?, board_id = ?, title = ?, content = ? where id = ?";
        jdbcTemplate.update(sql, article.getAuthorId(), article.getBoardId(), article.getTitle(), article.getContent(), article.getId());
        return article;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from article where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
