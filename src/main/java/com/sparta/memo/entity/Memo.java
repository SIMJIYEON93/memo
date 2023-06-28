package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Memo {
    private Long id;
    private String username;
    private String contents;

    public Memo(MemoRequestDto requestDto) { //cilent에게 받아온 데이터를 Memo에 넣어주기 위해
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
