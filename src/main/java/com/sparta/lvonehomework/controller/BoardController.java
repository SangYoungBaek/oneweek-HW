package com.sparta.lvonehomework.controller;

import com.sparta.lvonehomework.dto.BoardRequestDto;
import com.sparta.lvonehomework.dto.BoardResponseDto;
import com.sparta.lvonehomework.entity.Board;
import com.sparta.lvonehomework.service.BoardService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {
    private final JdbcTemplate jdbcTemplate;

    public BoardController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //게시물 작성 API
    @PostMapping("/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto){

        BoardService boardService = new BoardService(jdbcTemplate);
        return boardService.createBoard(requestDto);
    }
    //전체 게시글 목록 조회하기 API
    @GetMapping("/board")
    public List<BoardResponseDto> getBoard() {

        BoardService boardService = new BoardService(jdbcTemplate);
        return boardService.getBoard();
    }

    //선택한 게시글 조회 API
    @GetMapping("/board/{no}")
    public List<BoardResponseDto> selectgetBoard(@PathVariable Long no) {

        BoardService boardService = new BoardService(jdbcTemplate);
        return boardService.selectgetBoard(no);
    }

    //선택한 게시글 수정 API
    @PutMapping("/board/{no}")
    public BoardResponseDto updateBoard(@PathVariable Long no, @RequestBody BoardRequestDto requestDto){

        BoardService boardService = new BoardService(jdbcTemplate);
        return boardService.updateBoard(no, requestDto);
    }

    //선택한 게시글 삭제 API
    @DeleteMapping("/board/{no}")
    public String deleteBoard(@PathVariable Long no, @RequestBody BoardRequestDto requestDto) {
        BoardService boardService = new BoardService(jdbcTemplate);
        return boardService.deleteBoard(no, requestDto);
    }


}
