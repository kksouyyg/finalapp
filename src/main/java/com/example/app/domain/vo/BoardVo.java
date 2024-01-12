package com.example.app.domain.vo;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BoardVo {
    private Long boardNumber;
    private String boardTitle;
    private String boardContent;
    private String boardRegisterDate;
    private String boardUpdateDate;
    private Long userNumber;
    private String userId;  /* boardDto 전부 복사해서 붙여 넣고 요거하나 추가하자. */
    /*config xml 에서 boardVo 건다.*/
}
