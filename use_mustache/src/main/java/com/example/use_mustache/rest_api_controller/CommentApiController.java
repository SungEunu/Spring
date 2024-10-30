package com.example.use_mustache.rest_api_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.use_mustache.dto.CommentDto;
import com.example.use_mustache.service.CommentService;

@RestController
public class CommentApiController {
  @Autowired
  private CommentService commentService;

  //1. 댓글 조회 (부분 조회)
  @GetMapping("/api/articles/{id}/comments")  //데이터에 성공 여부에 따른 응답도 필요
  public ResponseEntity<List<CommentDto>> comments(@PathVariable("id") Long id){  // 데이터가 저장될 때는 dto -> entity -> service -> repository를 거친다면 데이터를 꺼낼때는 반대 과정을 거친다.
    /**
     * 서비스에 위임
     * 결과 응답
     */
    
    List<CommentDto> listComments = commentService.check(id);
    
    return ResponseEntity.status(HttpStatus.OK).body(listComments);
  }
  

  //2. 댓글 생성
  @PostMapping("/api/articles/{articleId}/comments")
  public ResponseEntity<CommentDto> create(@PathVariable("articleId") Long articleId, @RequestBody CommentDto dto){
   // 서비스에 위임
    CommentDto createdDto = commentService.create(articleId, dto);
   // 결과 응답
    return ResponseEntity.status(HttpStatus.OK).body(createdDto);
  }

  //3. 댓글 수정
  @PatchMapping("/api/comments/{id}")
  public ResponseEntity<CommentDto> update(@PathVariable("id") Long id, @RequestBody CommentDto dto){
    // 서비스에 위임
    CommentDto updateDto = commentService.update(id,dto);

    // 결과 응답
    return ResponseEntity.status(HttpStatus.OK).body(updateDto);
  }


  
  //4. 댓글 삭제 (id에 따른 댓글 삭제)
  @DeleteMapping("api/comments/{id}/delete")
  public ResponseEntity<CommentDto> delete(@PathVariable("id") Long id){
    CommentDto deleteDto = commentService.delete(id);
    if(deleteDto.getId() == null){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); 
    }
    return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
  }

  //4-1. 댓글 삭제 (article id에 따른 댓글 삭제)
  @DeleteMapping("api/comments/{article_id}/delete2")
  public ResponseEntity<CommentDto> delete2(@PathVariable("article_id") Long articleId){
    List<CommentDto> deleteDto = commentService.delete2(articleId);
    if(deleteDto.stream().anyMatch(value -> value.getArticle_id() == null)){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    return ResponseEntity.status(HttpStatus.OK).body(null);

  } // 80%(결과는 제대로 나오지만 뭔가 아쉬운 코드)

  

}
