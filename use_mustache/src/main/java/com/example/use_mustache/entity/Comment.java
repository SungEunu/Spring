package com.example.use_mustache.entity;

import java.util.Objects;

import com.example.use_mustache.dto.CommentDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor // Comment도 Article과 동일하게 작동을 하기는 한다. 하지만 Comment와 Article의 DB가 연동되어야 하기 때문에 DB 구조를 담당하는 Entity에서 Article을 참조해주는 것이다.
@Slf4j
public class Comment {

  @Id // 대표키 지정
  @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동으로 1씩 증가
  private Long id;
  
  @ManyToOne  // article과 comment가 n:1 관계라는 것을 보여준다.
  @JoinColumn(name="article_id")  // 해당 키가 외래키로 Article entity의 기본키와 매핑된다.
  private Article article;  // Article과 연결시켜주는 역할. Article과 Comment는 1 : n 관계를 유지할 것이다.
  
  @Column
  private String nickname;
  
  @Column
  private String body;

  public static Comment createComment(CommentDto dto, Article article) {
    log.info(dto.getArticle_id().toString());
    log.info(article.getId().toString());
    if(dto.getId() != null){
      throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id 오류가 발생");
    }
    if (dto.getArticle_id() != article.getId()) {
      throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id 오류");
    }
    
    return new Comment(
      dto.getId(),
      article,
      dto.getNickname(),
      dto.getBody()
    );
  }

  // // 댓글 수정 부분. 만약 부분만 고친다면 나머지는 남기는 방향으로 가야함
  // // 현재는 article_id와 id는 바꾸지 못한다는 가정이 있는 상태
  // public void patch(CommentDto dto) {
  //   if(!Objects.equals(this.id, dto.getId())){
  //     throw new IllegalArgumentException("댓글 수정 실패");
  //   } 
  //   if(dto.getNickname() != null){
  //     this.nickname = dto.getNickname();
  //   }
  //   if(dto.getBody()!= null){
  //     this.body = dto.getBody();
  //   }
  
  // }


  
  // 댓글 수정 부분. 만약 부분만 고친다면 나머지는 남기는 방향으로 가야함
  // 현재는 article_id와 id는 바꾸지 못한다는 가정이 있는 상태
  public void patch(CommentDto dto) {
    if(!Objects.equals(dto.getArticle_id(), this.article.getId())){
      throw new IllegalArgumentException("댓글 수정 실패 - article id 변환 불가");
    }
    if(!Objects.equals(this.id, dto.getId())){
      throw new IllegalArgumentException("댓글 수정 실패 - 고유 id 반환 불가");
    } 
    if(dto.getNickname() != null){
      this.nickname = dto.getNickname();
    }
    if(dto.getBody()!= null){
      this.body = dto.getBody();
    }
  
  }
}
