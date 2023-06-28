package com.sparta.memo.service;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class MemoService { //memoService 로 bean 등록됨

//    메서드 주입
//    private MemoRepository memoRepository;
//    public void setDi(){
//        this.memoRepository=memoRepository;
//    }


//    필드 주입
//    @Autowired
//    private MemoRepository memoRepository;

//   롬복으로 주입
//   @RequiredArgsConstructor
//   private final MemoRepository memoRepository;


//생성자 주입, 생성자가 1개일 때 @AutoWired 생략가능
    private MemoRepository memoRepository;
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }


    public MemoResponseDto createMemo(MemoRequestDto requestDto) {

        Memo memo = new Memo(requestDto);

        Memo saveMemo = memoRepository.save(memo);
        // saveMemo = memoRepository.
        // Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(saveMemo);
        //saveMemo, memo 동일 결과값 -> MemoRespository에서 save매소드에
        //setId가 있어서 50번째줄 memo객체에도 자동으로 id값이 반환되
        //따라서 savaMemo와 memo는 같은 기능을 구현함.

        return memoResponseDto;
        //requestDto로 data받아오고, responseDto로 응답해줘
    }


    public List<MemoResponseDto> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc().stream().map(MemoResponseDto::new).toList();
    }

    @Transactional
    public Long updateMemo(Long id, MemoRequestDto requestDto) { //optional 타입이여서 null 체크
        Memo memo  = findMemo(id);
            memo.update(requestDto);
            return id;

    }


    public Long deleteMemo(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findMemo(id);
            memoRepository.delete(memo);
            return id;
    }

    private Memo findMemo(Long id){
        return memoRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}
