package com.example.boardstudy.controller;


import com.example.boardstudy.dto.BoardEditRequestDto;
import com.example.boardstudy.dto.BoardRequestDto;
import com.example.boardstudy.response.Response;
import com.example.boardstudy.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    //게시글 전체 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/boards")
    public Response getBoards(){ return Response.success(boardService.getBoards());}
    //게시글 단건 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/boards/{id}")
    public Response getBoard(@PathVariable("id") Long id) {return Response.success(boardService.getBoard(id));}
    //게시글 작성
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/boards")
    public Response save(@Valid @RequestBody BoardRequestDto boardReq){
        return Response.success(boardService.save(boardReq));
    }
    //게시글 수정
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/boards/{id}")
    public Response editBoard(@PathVariable("id") Long id, @Valid @RequestBody BoardEditRequestDto boardEditReq){
        return Response.success(boardService.editBoard(id,boardEditReq));
    }
//    게시글 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/boards/{id}")
    public Response deleteBoard(@PathVariable("id") Long id){
        boardService.deleteBoard(id);
        return Response.success("삭제 완료");
    }
}
