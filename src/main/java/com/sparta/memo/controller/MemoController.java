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

    @PutMapping("/memos/{id}") //update, pathVariable 방식
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){ //반환은 업데이트 한 id값만
        //update할 id -> pathVariavble
        // httpbody부분에 json형태의 수정된 data -> requestDto

        //해당 메목 db에 존재하는지 확인
        if(memoList.containsKey(id)) {
            //해당 메모 가지고 오기
            Memo memo = memoList.get(id);

            //메모 수정
            memo.update(requestDto);
            return memo.getId();

        }else{
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id){
        //해당 메모가 존재하는지 확인
        if(memoList.containsKey(id)){
        memoList.remove(id);
        return id;
    }else{
            throw new IllegalArgumentException("해당 메모는 존재하지 않습니다.");
        }
}
}
