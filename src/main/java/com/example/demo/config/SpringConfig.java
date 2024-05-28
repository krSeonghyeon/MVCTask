package com.example.demo.config;

import com.example.demo.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public ArticleRepository articleRepository() {
        // return new ArticleRepositoryImpl();
        return new JdbcArticleRepositoryImpl(dataSource);
    }

    @Bean
    public BoardRepository boardRepository() {
        // return new BoardRepositoryImpl();
        return new JdbcBoardRepository(dataSource);
    }

    @Bean
    public MemberRepository memberRepository() {
        // return new MemberRepositoryImpl();
        return new JdbcMemberRepository(dataSource);
    }
}
