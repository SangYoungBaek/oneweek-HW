package com.sparta.lvonehomework.repository;

import com.sparta.lvonehomework.dto.BoardRequestDto;
import com.sparta.lvonehomework.dto.BoardResponseDto;
import com.sparta.lvonehomework.entity.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Board save(Board board) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO CRUD (id, title, contents, con_pw, save_date) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, board.getId());
                    preparedStatement.setString(2, board.getTitle());
                    preparedStatement.setString(3, board.getContents());
                    preparedStatement.setString(4, board.getCon_pw());
                    preparedStatement.setString(5, board.getSave_date());

                    return preparedStatement;
                },
                keyHolder);

        Long no =    keyHolder.getKey().longValue();
        board.setNo(no);

        return board;
    }

    public List<BoardResponseDto> findAll() {
        String sql = "SELECT * FROM CRUD ORDER BY save_date desc";
        return jdbcTemplate.query(sql, new RowMapper<BoardResponseDto>() {

            @Override
            public BoardResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long no = rs.getLong("no");
                String id = rs.getString("id");
                String title = rs.getString("title");
                String contents = rs.getString("contents");
                String con_pw = rs.getString("con_pw");
                String save_date = rs.getString("save_date");
                return new BoardResponseDto(no,id, title, contents, con_pw, save_date);
            }
        });
    }

    public List<BoardResponseDto> findOne(Long no) {
        String sql = "SELECT * FROM CRUD WHERE no = " + no;
        return jdbcTemplate.query(sql, new RowMapper<BoardResponseDto>() {

            @Override
            public BoardResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long no = rs.getLong("no");
                String id = rs.getString("id");
                String title = rs.getString("title");
                String contents = rs.getString("contents");
                String con_pw = rs.getString("con_pw");
                String save_date = rs.getString("save_date");
                return new BoardResponseDto(no,id, title, contents, con_pw, save_date);
            }
        });
    }

    public void update(Long no, BoardRequestDto requestDto) {
        String sql = "UPDATE CRUD SET id = ?, title = ?, contents = ? WHERE no = ?";
        jdbcTemplate.update(sql, requestDto.getId(), requestDto.getTitle(), requestDto.getContents(),no);
    }

    public void delete(Long no) {
        // memo 삭제
        String sql = "DELETE FROM CRUD WHERE no = ?";
        jdbcTemplate.update(sql, no);
    }

    public Board findByPw(Long no, String con_pw) {
        // DB 조회
        String sql = "SELECT * FROM CRUD WHERE no = ? AND con_pw = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Board board = new Board();
                board.setId(resultSet.getString("id"));
                board.setTitle(resultSet.getString("title"));
                board.setContents(resultSet.getString("contents"));
                board.setSave_date(resultSet.getString("save_date"));
                return board;
            } else {
                return null;
            }
        }, no, con_pw);
    }
}
