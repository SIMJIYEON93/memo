package com.sparta.memo.controller;


import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemoController {

        private final Map<Long, Memo> memoList = new HashMap<>();
    @PostMapping("/memos") // 메모생성하기 API
    //data가 httpbody에 json형태로 넘어올때 -> requestBody(요청은 body)
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto){ //body를 requestdto로 받는 다고 했어
       //RequestDto(data)를 entity(memo)로 바꾸는 코드
        Memo memo = new Memo(requestDto);

        // Memo의 Max_Id Check (id로 memo를 구분, id가 중복되면 x)
        Long max_Id = memoList.size()>0 ? Collections.max(memoList.keySet())+1 : 1;
        memo.setId(max_Id);

        // DB저장
        memoList.put(memo.getId(), memo);

        //entity(memo)를 responseDto로 변환
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;
        //requestDto로 data받아오고, responseDto로 응답해줘
    }

        @GetMapping("/memos")
    public List<MemoResponseDto> getMemos(){
        //Map to List
            List<MemoResponseDto> responseList = memoList.values().stream()
                    .map(MemoResponseDto::new).toList();

           return responseList;

        }
}
