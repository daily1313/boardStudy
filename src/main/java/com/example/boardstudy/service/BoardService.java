package com.example.boardstudy.service;


import com.example.boardstudy.dto.BoardEditRequestDto;
import com.example.boardstudy.dto.BoardRequestDto;
import com.example.boardstudy.dto.BoardResponseDto;
import com.example.boardstudy.entity.Board;
import com.example.boardstudy.exception.BoardNotFoundException;
import com.example.boardstudy.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
//의존성 주입
public class BoardService {
    private final BoardRepository boardRepository;
    //게시글 전체 조회
    @Transactional(readOnly = true)
    public List<BoardResponseDto>  getBoards(){
        List<Board> boards = boardRepository.findAll();
        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();
        for(Board board : boards){
            boardResponseDtoList.add(new BoardResponseDto().toDto(board));
        }
        return boardResponseDtoList;
    }
    //게시글 단건 조회
    @Transactional(readOnly = true)
    public BoardResponseDto getBoard(Long id){
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        return BoardResponseDto.toDto(board);
    }
    //게시글 작성
    @Transactional
    public BoardResponseDto save(BoardRequestDto req){
        Board board = new Board();
        board.setTitle(req.getTitle());
        board.setContent(req.getContent());
        board.setWriter(req.getWriter());
        boardRepository.save(board);
        return BoardResponseDto.toDto(board);
    }
    //Transactional 어노테이션 사용 이유 : 오류 발생시 rollback 연산 및 commit 기능 자동화
    //게시글 수정
    @Transactional
    public BoardResponseDto editBoard(Long id, BoardEditRequestDto req)
    {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);

        board.setTitle(req.getTitle());
        board.setContent(req.getContent());

        return BoardResponseDto.toDto(board);
    }
    //게시글 삭제
    public void deleteBoard(Long id)
    {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        boardRepository.delete(board);
    }

}
