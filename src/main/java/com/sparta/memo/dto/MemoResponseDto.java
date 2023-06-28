package com.sparta.memo.dto;

import com.sparta.memo.entity.Memo;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class MemoResponseDto { //memo entityclass와 비슷해
    private Long id;
    private String username;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public MemoResponseDto(Memo memo) { //memo에게 받아온 데이터를 reponseDto에 넣어주기 위해
        this.id = memo.getId();
        this.username = memo.getUsername();
        this.contents = memo.getContents();
        this.createdAt=memo.getCreatedAt();
        this.modifiedAt=memo.getModifiedAt();
    }
}