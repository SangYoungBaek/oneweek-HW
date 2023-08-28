package com.sparta.lvonehomework.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String id;
    private String title;
    private String contents;
    private String save_date;
    private String con_pw;
}
