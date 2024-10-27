package com.example.use_mustache.rest_api_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.use_mustache.dto.CommentDto;
import com.example.use_mustache.service.CommentService;

@RestController
public class CommentApiController {
  @Autowired
  private CommentService commentService;

  //1. 댓글 조회
  @GetMapping("/api/articles/{id}/comments")  //데이터에 성공 여부에 따른 응답도 필요
  public ResponseEntity<List<CommentDto>> comments(@PathVariable Long id){  // 데이터가 저장될 때는 dto -> entity -> service -> repository를 거친다면 데이터를 꺼낼때는 반대 과정을 거친다.
    /**
     * 서비스에 위임
     * 결과 응답
     */
    
    List<CommentDto> listComments = commentService.check(id);
    
    return ResponseEntity.status(HttpStatus.OK).body(listComments);
  }
  

  //2. 댓글 생성

  //3. 댓글 수정
  
  //4. 댓글 삭제
}
