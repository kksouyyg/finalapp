package com.example.app.domain.dto;

import lombok.Data;

@Data
public class BoardDto {
    private Long boardNumber;
    private String boardTitle;
    private String boardContent;
    private String boardRegisterDate;
    private String boardUpdateDate;
    private Long userNumber;
}
