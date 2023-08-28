package com.sparta.lvonehomework.dto;

import com.sparta.lvonehomework.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long no;
    private String id;
    private String title;
    private String contents;
    private String save_date;
    private String con_pw;


    public BoardResponseDto(Board board) {
        this.no = board.getNo();
        this.id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.save_date = board.getSave_date();
        this.con_pw = board.getCon_pw();
    }

    public BoardResponseDto(Long no, String id, String title, String contents, String con_pw, String save_date) {
        this.no = no;
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.con_pw = con_pw;
        this.save_date = save_date;
    }
}
