package com.example.use_mustache.rest_api_controller;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.use_mustache.dto.ArticleForm;
import com.example.use_mustache.entity.Article;
import com.example.use_mustache.service.ArticleService;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class ArticleApiController {
  // 원래 쉬운 코드에서는 필요 없는 단계지만 복잡하면 꼭 필요한게 Service 계층이다
  @Autowired
  private ArticleService articleService;

  //GET
  @GetMapping("/api/articles")
  public List<Article> index(){
    return articleService.index();  // index 메서드를 생성해서 repository와 서비스가 연결되도록 한 것이다.
    
  }

  @GetMapping("/api/articles/{id}")
  public Article show(@PathVariable("id") Long id){
    return articleService.show(id);
    
  }

  //POST
  @PostMapping("/api/articles")
  public ResponseEntity<Article> create(@RequestBody ArticleForm dto){  // 본문의 내용을 받아올 수 있음
    Article created = articleService.create(dto);
    return (created != null) ?
      ResponseEntity.status(HttpStatus.OK).body(created) :
      ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }


  //PATCH
  @PatchMapping("/api/articles/{id}")
  public ResponseEntity<Article> update(@PathVariable("id") Long id, @RequestBody ArticleForm dto){
    Article updated = articleService.update(id, dto);
    return (updated != null) ?
      ResponseEntity.status(HttpStatus.OK).body(updated) :
      ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    
  }
  
  
  //DELETE
  @DeleteMapping("/api/articles/{id}")
  public ResponseEntity<Article> delete(@PathVariable("id") Long id){
    Article delete = articleService.delete(id);
    return(delete != null)?
      ResponseEntity.status(HttpStatus.NO_CONTENT).build() :  // Repository에 존재해서 지워진 상태.
      ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // Repository에 존재하지 않아서 에러가 난 상태.
  }


  // Service에는 Repository와 연동되는 부분을 옮김

  // 트랜젝션
  /**
   * 일련의 과정이 끝나지 않고 실패하면 다시 처음으로 돌아가는 상황이 발생한다.
   * Ex.
   * 1. 게시판에 데이터 3개를 한꺼번에 생성 요청
   * 2. 데이터를 DB에 저장하는 과정에서 의도적인 오류 발생시키기
   * 3. 롤백상황 확인하기
   */

  // 1. 게시판에 데이터 3개를 한꺼번에 생성 요청하는 코드
  @PostMapping("/api/transaction-test")
  public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
    List<Article> createdList = articleService.createArticles(dtos);
    return (createdList != null) ?
      ResponseEntity.status(HttpStatus.OK).body(createdList) :
      ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }
}