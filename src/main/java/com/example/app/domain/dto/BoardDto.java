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

/* dto는 db랑 똑같이 만들고 다른건 vo로 만들자 */
/* 여기는 userId가 없어서 BoardVo로 만들자 */
