package com.sparta.lvonehomework.entity;

import com.sparta.lvonehomework.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
글번호 - no
제목 - title
작성자ID - id
작성내용 - contents
작성날짜 - save_date
비밀번호 - con_pw
 */

@Getter
@Setter
@NoArgsConstructor
public class Board {
    private Long no;
    private String id;
    private String title;
    private String contents;
    private String save_date;
    private String con_pw;

    public Board(BoardRequestDto requestDto) {
        this.id = requestDto.getId();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.save_date = requestDto.getSave_date();
        this.con_pw = requestDto.getCon_pw();
    }
}
