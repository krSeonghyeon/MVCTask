package com.example.demo.repository;

import com.example.demo.domain.Member;
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
public class JdbcMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member (name, email, password) values (?, ?, ?)";
        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setString(3, member.getPassword());
            return ps;
        }, keyholder);

        Long id = keyholder.getKey().longValue();
        member.setId(id);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select id, name, email, password from member where id = ?";

        try {
            Member member = jdbcTemplate.queryForObject(sql, memberRowMapper(), id);
            return Optional.of(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> new Member(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password")
        );
    }

    @Override
    public List<Member> findAll() {
        String sql = "select id, name, email, password from member";
        return jdbcTemplate.query(sql, memberRowMapper());
    }

    @Override
    public Member update(Member member) {
        String sql = "update member set name = ?, email = ?, password = ? where id = ?";
        jdbcTemplate.update(sql, member.getName(), member.getEmail(), member.getPassword(), member.getId());
        return member;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from member where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
