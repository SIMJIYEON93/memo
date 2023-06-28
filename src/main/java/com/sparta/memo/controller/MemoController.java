package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.service.MemoService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class MemoController {

    private MemoService memoService;

    public MemoController(JdbcTemplate jdbcTemplate) {
        this.memoService = new MemoService(jdbcTemplate);
    }

    @PostMapping("/memos") // 메모생성하기 API
    //data가 httpbody에 json형태로 넘어올때 -> requestBody(요청은 body)
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto){ //body를 requestdto로 받는 다고 했어
       //RequestDto(data)를 entity(memo)로 바꾸는 코드
        //MemoService에서도 jdbc   template를 생성자로 받고 있어서

        return memoService.CreateMemo(requestDto);
    }

        @GetMapping("/memos")
    public List<MemoResponseDto> getMemos(){
        return memoService.getMemos();
    }

    @PutMapping("/memos/{id}") //update, pathVariable 방식
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){ //반환은 업데이트 한 id값만
        //update할 id -> pathVariavble
        // httpbody부분에 json형태의 수정된 data -> requestDto
        return memoService.updateMemo(id, requestDto);
    }

    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        return memoService.deleteMemo(id);
    }
}
