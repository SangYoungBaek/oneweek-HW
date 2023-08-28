package com.sparta.lvonehomework.service;

import com.sparta.lvonehomework.dto.BoardRequestDto;
import com.sparta.lvonehomework.dto.BoardResponseDto;
import com.sparta.lvonehomework.entity.Board;
import com.sparta.lvonehomework.repository.BoardRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BoardService {
    private final JdbcTemplate jdbcTemplate;

    public BoardService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);

        BoardRepository boardRepository = new BoardRepository(jdbcTemplate);
        Board saveBoard = boardRepository.save(board);

        BoardResponseDto boardResponseDto = new BoardResponseDto(saveBoard);

        return boardResponseDto;
    }

    public List<BoardResponseDto> getBoard() {

        BoardRepository boardRepository = new BoardRepository(jdbcTemplate);
        return boardRepository.findAll();
    }

    public List<BoardResponseDto> selectgetBoard(Long no) {
        BoardRepository boardRepository = new BoardRepository(jdbcTemplate);
        return boardRepository.findOne(no);
    }

    public BoardResponseDto updateBoard(Long no, BoardRequestDto requestDto) {
        BoardRepository boardRepository = new BoardRepository(jdbcTemplate);

        Board board = boardRepository.findByPw(no, requestDto.getCon_pw());
        if(board != null){

            boardRepository.update(no, requestDto);

            BoardResponseDto boardResponseDto = new BoardResponseDto(no, requestDto.getId(), requestDto.getTitle(), requestDto.getContents(), requestDto.getCon_pw(), requestDto.getSave_date());

            return boardResponseDto;
        } else {
            throw new IllegalArgumentException("선택한 게시물은 존재하지 않거나 비밀번호가 잘못입력해주셨습니다.");
        }
    }



    public String deleteBoard(Long no, BoardRequestDto requestDto) {
        BoardRepository boardRepository = new BoardRepository(jdbcTemplate);
        Board board = boardRepository.findByPw(no, requestDto.getCon_pw());
        if(board != null) {

            boardRepository.delete(no);

            return no + "번 게시물 삭제에 성공했습니다.";
        } else {
            throw new IllegalArgumentException("선택한 게시물은 존재하지 않거나 비밀번호가 잘못입력해주셨습니다.");
        }
    }
}
