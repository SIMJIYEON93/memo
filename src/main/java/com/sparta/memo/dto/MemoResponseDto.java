package com.sparta.memo.dto;

import com.sparta.memo.entity.Memo;
import lombok.Getter;

@Getter
public class MemoResponseDto { //memo entityclass와 비슷해
    private Long id;
    private String username;
    private String contents;

    public MemoResponseDto(Memo memo) { //memo에게 받아온 데이터를 reponseDto에 넣어주기 위해
        this.id = memo.getId();
        this.username = memo.getUsername();
        this.contents = memo.getContents();
    }

    public MemoResponseDto(Long id, String username, String contents) {
        this.id =id;
        this.username=username;
        this.contents =contents;
    }
}